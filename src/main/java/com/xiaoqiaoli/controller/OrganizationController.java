package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.entity.Organization;
import com.xiaoqiaoli.service.AccountLocalService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hanlei6 on 2016/7/20.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController<Organization> {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationLocalService organizationService;

    @Autowired
    private GenerateIdRemoteService generateIdRemoteService;

    @Autowired
    private AccountLocalService accountService;

    @RequestMapping("/index")
    public String index() {
        return "organization/index";
    }

    @RequestMapping("/tree")
    public
    @ResponseBody
    List<Map<String, Object>> tree(String id) {
        if (StringUtils.isEmpty(id)) {
            id = "0";
        }
        List<Organization> organizations = organizationService.localFindByParent(id);
        List<Map<String, Object>> results = new ArrayList<>();
        organizations.forEach(organizationDO -> {
            Map<String, Object> result = new HashMap<>();
            result.put("id", organizationDO.getId());
            result.put("name", organizationDO.getName());
            result.put("isParent", true);
            results.add(result);
        });
        return results;
    }

    @RequestMapping("/add")
    public String add() {
        return "organization/add";
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") String id, Model model) {
        Organization organization = organizationService.localGet(id);
        model.addAttribute("organization", organization);
        return "organization/view";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") String id, Model model) {
        Organization organization = organizationService.localGet(id);
        model.addAttribute("organization", organization);
        return "organization/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(Organization organization) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        organization.setStatus(Constant.PERSISTENT_OBJECT_STATUS_ACTIVE);
        organization.setCreator(account);
        organization.setModifier(account);
        organization.setId(generateIdRemoteService.get(Constant.APPLICATION, Module.ORGANIZATION.name()));
        Organization organizationDO = organizationService.insert(organization);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(organizationDO, result);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> update(Organization organization) {
        Map<String, Object> result = new HashMap<>();
        Organization exist = organizationService.localGet(organization.getId());
        if (organization.getVersion() != exist.getVersion()) {
            LOGGER.error("请求中参数版本与最新版本存在差异,保存失败,失败参数{}", organization);
            super.buildResponseStatus(null, result);
            return result;
        }

        BeanUtils.copyProperties(organization, exist, "creator", "created");

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        exist.setModifier(account);
        Organization organizationDO = organizationService.update(exist);
        buildResponseStatus(organizationDO, result);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam("ids") String ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        Map<String, Object> result = new HashMap<>();
        List<Organization> organizations = organizationService.localFindByIds(ids.split(","));
        organizations.forEach(organization -> {
            organization.setModified(new Date());
            organization.setModifier(account);
            organization.setStatus("0");
        });
        try {
            organizationService.batchDelete(organizations);
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
