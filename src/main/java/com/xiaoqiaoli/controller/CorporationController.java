package com.xiaoqiaoli.controller;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.entity.Corporation;
import com.xiaoqiaoli.service.CorporationLocalService;
import com.xiaoqiaoli.service.client.GenerateIdRemoteService;
import com.xiaoqiaoli.util.Constant;
import com.xiaoqiaoli.util.MD5Util;
import com.xiaoqiaoli.util.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/20.
 */
@Controller
@RequestMapping("/corporation")
public class CorporationController extends BaseController<Corporation> {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationController.class);

    @Autowired
    private CorporationLocalService corporationService;

    @Autowired
    private GenerateIdRemoteService generateIdRemoteService;

    @Value("${ext.image.dir}")
    private String imageDir;

    @RequestMapping("/index")
    public String index(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, Corporation corporation, Model model) {
        Page<Corporation> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page = corporationService.localPage(page, corporation.getName(), corporation.getContact(), corporation.getLegalPerson());
        model.addAttribute("page", page);
        return "corporation/index";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAllAttributes(provincesAndCities());
        return "corporation/add";
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") String id, Model model) {
        Corporation corporation = corporationService.localGet(id);
        model.addAttribute("corporation", corporation);
        return "corporation/view";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") String id, Model model) {
        model.addAllAttributes(provincesAndCities());
        Corporation corporation = corporationService.localGet(id);
        model.addAttribute("corporation", corporation);
        return "corporation/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(MultipartFile logoFile, MultipartFile blcFile, MultipartFile trccFile, MultipartFile occFile, MultipartFile aolcFile, MultipartFile lpicucFile,
                             MultipartFile lpicdcFile, MultipartFile cicucFile, MultipartFile cicdcFile, Corporation corporation) {
        corporation = uploadCopy(corporation, logoFile, blcFile, trccFile, occFile, aolcFile, lpicucFile, lpicdcFile, cicucFile, cicdcFile);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        corporation.setStatus(Constant.PERSISTENT_OBJECT_STATUS_ACTIVE);
        corporation.setCreator(principal.getUsername());
        corporation.setModifier(principal.getUsername());
        corporation.setId(generateIdRemoteService.get(Constant.APPLICATION, Module.CORPORATION.name()));
        Corporation corporationDO = corporationService.insert(corporation);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(corporationDO, result);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> update(MultipartFile logoFile, MultipartFile blcFile, MultipartFile trccFile, MultipartFile occFile, MultipartFile aolcFile, MultipartFile lpicucFile,
                               MultipartFile lpicdcFile, MultipartFile cicucFile, MultipartFile cicdcFile, Corporation corporation) {
        Map<String,Object> result = new HashMap<>();
        corporation = uploadCopy(corporation, logoFile, blcFile, trccFile, occFile, aolcFile, lpicucFile, lpicdcFile, cicucFile, cicdcFile);

        Corporation exist = corporationService.localGet(corporation.getId());
        if(corporation.getVersion() != exist.getVersion()) {
            LOGGER.error("请求中参数版本与最新版本存在差异,保存失败,失败参数{}", corporation);
            super.buildResponseStatus(null, result);
            return result;
        }

        if (StringUtils.isEmpty(corporation.getBusinessLicenceCopy())) {
            corporation.setBusinessLicenceCopy(exist.getBusinessLicenceCopy());
        }
        if (StringUtils.isEmpty(corporation.getTaxRegistrationCertificateCopy())) {
            corporation.setTaxRegistrationCertificateCopy(exist.getTaxRegistrationCertificateCopy());
        }
        if (StringUtils.isEmpty(corporation.getOrganizationCodeCertificateCopy())) {
            corporation.setOrganizationCodeCertificateCopy(exist.getOrganizationCodeCertificateCopy());
        }
        if (StringUtils.isEmpty(corporation.getAccountOpeningLicenseCopy())) {
            corporation.setAccountOpeningLicenseCopy(exist.getAccountOpeningLicenseCopy());
        }
        if (StringUtils.isEmpty(corporation.getLegalPersonIdentificationCardUpCopy())) {
            corporation.setLegalPersonIdentificationCardUpCopy(exist.getLegalPersonIdentificationCardUpCopy());
        }
        if (StringUtils.isEmpty(corporation.getLegalPersonIdentificationCardDownCopy())) {
            corporation.setLegalPersonIdentificationCardDownCopy(exist.getLegalPersonIdentificationCardDownCopy());
        }
        if (StringUtils.isEmpty(corporation.getContactIdentificationCardUpCopy())) {
            corporation.setContactIdentificationCardUpCopy(exist.getContactIdentificationCardUpCopy());
        }
        if (StringUtils.isEmpty(corporation.getContactIdentificationCardDownCopy())) {
            corporation.setContactIdentificationCardDownCopy(exist.getContactIdentificationCardDownCopy());
        }
        BeanUtils.copyProperties(corporation, exist, "creator", "created");

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        exist.setModifier(principal.getUsername());
        Corporation corporationDO = corporationService.update(exist);
        buildResponseStatus(corporationDO, result);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam("ids") String ids) {
        Map<String, Object> result = new HashMap<>();
        try {
            corporationService.batchDelete(ids.split(","));
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

    private Corporation uploadCopy(Corporation corporation, MultipartFile logoFile, MultipartFile blcFile, MultipartFile trccFile, MultipartFile occFile, MultipartFile aolcFile, MultipartFile lpicucFile,
                                   MultipartFile lpicdcFile, MultipartFile cicucFile, MultipartFile cicdcFile) {
        // 营业执照副本
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                String file = "logoFile" + corporation.getName() + logoFile.getOriginalFilename();
                corporation.setLogo(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(logoFile.getInputStream(), Paths.get(imageDir, corporation.getLogo()));
            } catch (IOException e) {
                LOGGER.error("上传LOGO出现错误,参数：{}", corporation);
            }
        }
        // 营业执照副本
        if (blcFile != null && !blcFile.isEmpty()) {
            try {
                String file = "businessLicenceCopy" + corporation.getName() + corporation.getBusinessLicence() + blcFile.getOriginalFilename();
                corporation.setBusinessLicenceCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(blcFile.getInputStream(), Paths.get(imageDir, corporation.getBusinessLicenceCopy()));
            } catch (IOException e) {
                LOGGER.error("上传营业执照副本出现错误,参数：{}", corporation);
            }
        }
        // 税务登记号副本
        if (trccFile != null && !trccFile.isEmpty()) {
            try {
                String file = "taxRegistrationCertificateCopy" + corporation.getName() + corporation.getTaxRegistrationCertificate() + trccFile.getOriginalFilename();
                corporation.setTaxRegistrationCertificateCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(trccFile.getInputStream(), Paths.get(imageDir, corporation.getTaxRegistrationCertificateCopy()));
            } catch (IOException e) {
                LOGGER.error("上传税务登记号副本出现错误,参数：{}", corporation);
            }
        }
        // 组织机构代码证副本
        if (occFile != null && !occFile.isEmpty()) {
            try {
                String file = "organizationCodeCertificateCopy" + corporation.getName() + corporation.getOrganizationCodeCertificate() + occFile.getOriginalFilename();
                corporation.setOrganizationCodeCertificateCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(occFile.getInputStream(), Paths.get(imageDir, corporation.getOrganizationCodeCertificateCopy()));
            } catch (IOException e) {
                LOGGER.error("上传组织机构代码证副本出现错误,参数：{}", corporation);
            }
        }
        // 银行开户副本
        if (aolcFile != null && !aolcFile.isEmpty()) {
            try {
                String file = "accountOpeningLicenseCopy" + corporation.getName() + corporation.getAccountOpeningLicense() + aolcFile.getOriginalFilename();
                corporation.setAccountOpeningLicenseCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(aolcFile.getInputStream(), Paths.get(imageDir, corporation.getAccountOpeningLicenseCopy()));
            } catch (IOException e) {
                LOGGER.error("上传银行开户副本出现错误,参数：{}", corporation);
            }
        }
        // 法人身份证正面
        if (lpicucFile != null && !lpicucFile.isEmpty()) {
            try {
                String file = "legalPersonIdentificationCardUpCopy" + corporation.getName() + corporation.getLegalPersonIdNumber() + lpicucFile.getOriginalFilename();
                corporation.setLegalPersonIdentificationCardUpCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(lpicucFile.getInputStream(), Paths.get(imageDir, corporation.getLegalPersonIdentificationCardUpCopy()));
            } catch (IOException e) {
                LOGGER.error("上传法人身份证正面出现错误,参数：{}", corporation);
            }
        }
        // 法人身份证背面
        if (lpicdcFile != null && !lpicdcFile.isEmpty()) {
            try {
                String file = "legalPersonIdentificationCardDownCopy" + corporation.getName() + corporation.getLegalPersonIdNumber() + lpicdcFile.getOriginalFilename();
                corporation.setLegalPersonIdentificationCardDownCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(lpicdcFile.getInputStream(), Paths.get(imageDir, corporation.getLegalPersonIdentificationCardDownCopy()));
            } catch (IOException e) {
                LOGGER.error("上传法人身份证背面出现错误,参数：{}", corporation);
            }
        }
        // 企业联系人身份证正面
        if (cicucFile != null && !cicucFile.isEmpty()) {
            try {
                String file = "contactIdentificationCardUpCopy" + corporation.getName() + corporation.getContactIdNumber() + cicucFile.getOriginalFilename();
                corporation.setContactIdentificationCardUpCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(cicucFile.getInputStream(), Paths.get(imageDir, corporation.getContactIdentificationCardUpCopy()));
            } catch (IOException e) {
                LOGGER.error("上传企业联系人身份证正面出现错误,参数：{}", corporation);
            }
        }
        // 企业联系人身份证背面
        if (cicdcFile != null && !cicdcFile.isEmpty()) {
            try {
                String file = "contactIdentificationCardDownCopy" + corporation.getName() + corporation.getContactIdNumber() + cicdcFile.getOriginalFilename();
                corporation.setContactIdentificationCardDownCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(cicdcFile.getInputStream(), Paths.get(imageDir, corporation.getContactIdentificationCardDownCopy()));
            } catch (IOException e) {
                LOGGER.error("上传企业联系人身份证背面出现错误,参数：{}", corporation);
            }
        }
        return corporation;
    }
}
