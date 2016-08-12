package com.xiaoqiaoli.controller;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.domain.OrganizationDO;
import com.xiaoqiaoli.service.OrganizationLocalService;
import com.xiaoqiaoli.service.client.GenerateIdRemoteService;
import com.xiaoqiaoli.util.Constant;
import com.xiaoqiaoli.util.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/20.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController<OrganizationDO> {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationLocalService organizationService;

    @Autowired
    private GenerateIdRemoteService generateIdRemoteService;

    @RequestMapping("/index")
    public String index(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, String parentId, Model model) {
        Page<OrganizationDO> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page = organizationService.localPage(page, parentId);
        model.addAttribute("page", page);
        return "organization/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "organization/add";
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") String id, Model model) {
        OrganizationDO organization = organizationService.localGet(id);
        model.addAttribute("organization", organization);
        return "organization/view";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") String id, Model model) {
        OrganizationDO organization = organizationService.localGet(id);
        model.addAttribute("organization", organization);
        return "organization/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(OrganizationDO organization) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        organization.setStatus(Constant.PERSISTENT_OBJECT_STATUS_ACTIVE);
        organization.setCreator(principal.getUsername());
        organization.setModifier(principal.getUsername());
        organization.setId(generateIdRemoteService.get(Constant.APPLICATION, Module.CORPORATION.name()));
        OrganizationDO organizationDO = organizationService.insert(organization);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(organizationDO, result);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> update(OrganizationDO organization) {
        Map<String, Object> result = new HashMap<>();
        OrganizationDO exist = organizationService.localGet(organization.getId());
        if (organization.getVersion() != exist.getVersion()) {
            LOGGER.error("请求中参数版本与最新版本存在差异,保存失败,失败参数{}", organization);
            super.buildResponseStatus(null, result);
            return result;
        }

        BeanUtils.copyProperties(organization, exist, "creator", "created");

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        exist.setModifier(principal.getUsername());
        OrganizationDO organizationDO = organizationService.update(exist);
        buildResponseStatus(organizationDO, result);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam("ids") String ids) {
        Map<String, Object> result = new HashMap<>();
        try {
            organizationService.batchDelete(ids.split(","));
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_SUCCESS);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_SUCCESS);
        } catch (RuntimeException e) {
            LOGGER.error("批量删除出现错误,参数：{}", ids);
            result.put(Constant.RETURN_MAP_KEY_STATUS, Constant.RETURN_MAP_VALUE_STATUS_FAILURE);
            result.put(Constant.RETURN_MAP_KEY_MESSAGE, Constant.RETURN_MAP_VALUE_MESSAGE_INSERT_FAILURE);
        }
        return result;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
