package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.entity.Account;
import com.xiaoqiaoli.service.AccountLocalService;
import com.xiaoqiaoli.service.CorporationLocalService;
import com.xiaoqiaoli.service.client.GenerateIdRemoteService;
import com.xiaoqiaoli.util.Constant;
import com.xiaoqiaoli.util.MD5Util;
import com.xiaoqiaoli.util.Module;
import com.xiaoqiaoli.vo.CorporationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/20.
 */
@Controller
@RequestMapping("/corporation")
public class CorporationController extends BaseController<CorporationVO> {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationController.class);

    @Autowired
    private CorporationLocalService corporationService;

    @Autowired
    private GenerateIdRemoteService generateIdRemoteService;

    @Autowired
    private AccountLocalService accountService;

    @Value("${ext.image.dir}")
    private String imageDir;

    @RequestMapping("/index")
    public String index() {
        return "corporation/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "corporation/add";
    }

    @RequestMapping("/view")
    public String view(@RequestParam("id") String id, Model model) {
        CorporationVO corporationVO = corporationService.localGet(id);
        model.addAttribute("corporation", corporationVO);
        return "corporation/view";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") String id, Model model) {
        CorporationVO corporationVO = corporationService.localGet(id);
        model.addAttribute("corporation", corporationVO);
        return "corporation/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(MultipartFile logoFile, MultipartFile blcFile, MultipartFile trccFile, MultipartFile occFile, MultipartFile aolcFile, MultipartFile lpicucFile,
                             MultipartFile lpicdcFile, MultipartFile cicucFile, MultipartFile cicdcFile, CorporationVO corporationVO) {
        corporationVO = uploadCopy(corporationVO, logoFile, blcFile, trccFile, occFile, aolcFile, lpicucFile, lpicdcFile, cicucFile, cicdcFile);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        corporationVO.setStatus(Constant.PERSISTENT_OBJECT_STATUS_ACTIVE);
        corporationVO.setCreator(account);
        corporationVO.setModifier(account);
        corporationVO.setId(generateIdRemoteService.get(Constant.APPLICATION, Module.CORPORATION.name()));
        CorporationVO corporationDO = corporationService.insert(corporationVO);
        Map<String, Object> result = new HashMap<>();
        buildResponseStatus(corporationDO, result);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> update(MultipartFile logoFile, MultipartFile blcFile, MultipartFile trccFile, MultipartFile occFile, MultipartFile aolcFile, MultipartFile lpicucFile,
                               MultipartFile lpicdcFile, MultipartFile cicucFile, MultipartFile cicdcFile, CorporationVO corporationVO) {
        Map<String, Object> result = new HashMap<>();
        corporationVO = uploadCopy(corporationVO, logoFile, blcFile, trccFile, occFile, aolcFile, lpicucFile, lpicdcFile, cicucFile, cicdcFile);

//        CorporationVO exist = corporationService.localGet(corporationVO.getId());
//        if (corporationVO.getVersion() != exist.getVersion()) {
//            LOGGER.error("请求中参数版本与最新版本存在差异,保存失败,失败参数{}", corporationVO);
//            super.buildResponseStatus(null, result);
//            return result;
//        }
//
//        if (StringUtils.isEmpty(corporationVO.getBusinessLicenceCopy())) {
//            corporationVO.setBusinessLicenceCopy(exist.getBusinessLicenceCopy());
//        }
//        if (StringUtils.isEmpty(corporationVO.getTaxRegistrationCertificateCopy())) {
//            corporationVO.setTaxRegistrationCertificateCopy(exist.getTaxRegistrationCertificateCopy());
//        }
//        if (StringUtils.isEmpty(corporationVO.getOrganizationCodeCertificateCopy())) {
//            corporationVO.setOrganizationCodeCertificateCopy(exist.getOrganizationCodeCertificateCopy());
//        }
//        if (StringUtils.isEmpty(corporationVO.getAccountOpeningLicenseCopy())) {
//            corporationVO.setAccountOpeningLicenseCopy(exist.getAccountOpeningLicenseCopy());
//        }
//        if (StringUtils.isEmpty(corporationVO.getLegalPersonIdentificationCardUpCopy())) {
//            corporationVO.setLegalPersonIdentificationCardUpCopy(exist.getLegalPersonIdentificationCardUpCopy());
//        }
//        if (StringUtils.isEmpty(corporationVO.getLegalPersonIdentificationCardDownCopy())) {
//            corporationVO.setLegalPersonIdentificationCardDownCopy(exist.getLegalPersonIdentificationCardDownCopy());
//        }
//        if (StringUtils.isEmpty(corporationVO.getContactIdentificationCardUpCopy())) {
//            corporationVO.setContactIdentificationCardUpCopy(exist.getContactIdentificationCardUpCopy());
//        }
//        if (StringUtils.isEmpty(corporationVO.getContactIdentificationCardDownCopy())) {
//            corporationVO.setContactIdentificationCardDownCopy(exist.getContactIdentificationCardDownCopy());
//        }
//        BeanUtils.copyProperties(corporationVO, exist, "creator", "created");

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        corporationVO.setModifier(account);
        corporationVO.setModified(new Date());
        corporationVO = corporationService.update(corporationVO);
        buildResponseStatus(corporationVO, result);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam("ids") String ids) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.localGetByUsername(principal.getUsername());
        Map<String, Object> result = new HashMap<>();
        List<CorporationVO> corporationVOs = corporationService.localFindByIds(ids.split(","));
        corporationVOs.forEach(corporationVO -> {
            corporationVO.setModified(new Date());
            corporationVO.setModifier(account);
            corporationVO.setStatus("0");
        });
        try {
            corporationService.batchDelete(corporationVOs);
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

    private CorporationVO uploadCopy(CorporationVO corporationVO, MultipartFile logoFile, MultipartFile blcFile, MultipartFile trccFile, MultipartFile occFile, MultipartFile aolcFile, MultipartFile lpicucFile,
                                     MultipartFile lpicdcFile, MultipartFile cicucFile, MultipartFile cicdcFile) {
        // 营业执照副本
        if (logoFile != null && !logoFile.isEmpty()) {
            try {
                String file = "logoFile" + corporationVO.getName() + logoFile.getOriginalFilename();
                corporationVO.setLogo(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(logoFile.getInputStream(), Paths.get(imageDir, corporationVO.getLogo()));
            } catch (IOException e) {
                LOGGER.error("上传LOGO出现错误,参数：{}", corporationVO);
            }
        }
        // 营业执照副本
        if (blcFile != null && !blcFile.isEmpty()) {
            try {
                String file = "businessLicenceCopy" + corporationVO.getName() + corporationVO.getBusinessLicence() + blcFile.getOriginalFilename();
                corporationVO.setBusinessLicenceCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(blcFile.getInputStream(), Paths.get(imageDir, corporationVO.getBusinessLicenceCopy()));
            } catch (IOException e) {
                LOGGER.error("上传营业执照副本出现错误,参数：{}", corporationVO);
            }
        }
        // 税务登记号副本
        if (trccFile != null && !trccFile.isEmpty()) {
            try {
                String file = "taxRegistrationCertificateCopy" + corporationVO.getName() + corporationVO.getTaxRegistrationCertificate() + trccFile.getOriginalFilename();
                corporationVO.setTaxRegistrationCertificateCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(trccFile.getInputStream(), Paths.get(imageDir, corporationVO.getTaxRegistrationCertificateCopy()));
            } catch (IOException e) {
                LOGGER.error("上传税务登记号副本出现错误,参数：{}", corporationVO);
            }
        }
        // 组织机构代码证副本
        if (occFile != null && !occFile.isEmpty()) {
            try {
                String file = "organizationCodeCertificateCopy" + corporationVO.getName() + corporationVO.getOrganizationCodeCertificate() + occFile.getOriginalFilename();
                corporationVO.setOrganizationCodeCertificateCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(occFile.getInputStream(), Paths.get(imageDir, corporationVO.getOrganizationCodeCertificateCopy()));
            } catch (IOException e) {
                LOGGER.error("上传组织机构代码证副本出现错误,参数：{}", corporationVO);
            }
        }
        // 银行开户副本
        if (aolcFile != null && !aolcFile.isEmpty()) {
            try {
                String file = "accountOpeningLicenseCopy" + corporationVO.getName() + corporationVO.getAccountOpeningLicense() + aolcFile.getOriginalFilename();
                corporationVO.setAccountOpeningLicenseCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(aolcFile.getInputStream(), Paths.get(imageDir, corporationVO.getAccountOpeningLicenseCopy()));
            } catch (IOException e) {
                LOGGER.error("上传银行开户副本出现错误,参数：{}", corporationVO);
            }
        }
        // 法人身份证正面
        if (lpicucFile != null && !lpicucFile.isEmpty()) {
            try {
                String file = "legalPersonIdentificationCardUpCopy" + corporationVO.getName() + corporationVO.getLegalPersonIdNumber() + lpicucFile.getOriginalFilename();
                corporationVO.setLegalPersonIdentificationCardUpCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(lpicucFile.getInputStream(), Paths.get(imageDir, corporationVO.getLegalPersonIdentificationCardUpCopy()));
            } catch (IOException e) {
                LOGGER.error("上传法人身份证正面出现错误,参数：{}", corporationVO);
            }
        }
        // 法人身份证背面
        if (lpicdcFile != null && !lpicdcFile.isEmpty()) {
            try {
                String file = "legalPersonIdentificationCardDownCopy" + corporationVO.getName() + corporationVO.getLegalPersonIdNumber() + lpicdcFile.getOriginalFilename();
                corporationVO.setLegalPersonIdentificationCardDownCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(lpicdcFile.getInputStream(), Paths.get(imageDir, corporationVO.getLegalPersonIdentificationCardDownCopy()));
            } catch (IOException e) {
                LOGGER.error("上传法人身份证背面出现错误,参数：{}", corporationVO);
            }
        }
        // 企业联系人身份证正面
        if (cicucFile != null && !cicucFile.isEmpty()) {
            try {
                String file = "contactIdentificationCardUpCopy" + corporationVO.getName() + corporationVO.getContactIdNumber() + cicucFile.getOriginalFilename();
                corporationVO.setContactIdentificationCardUpCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(cicucFile.getInputStream(), Paths.get(imageDir, corporationVO.getContactIdentificationCardUpCopy()));
            } catch (IOException e) {
                LOGGER.error("上传企业联系人身份证正面出现错误,参数：{}", corporationVO);
            }
        }
        // 企业联系人身份证背面
        if (cicdcFile != null && !cicdcFile.isEmpty()) {
            try {
                String file = "contactIdentificationCardDownCopy" + corporationVO.getName() + corporationVO.getContactIdNumber() + cicdcFile.getOriginalFilename();
                corporationVO.setContactIdentificationCardDownCopy(MD5Util.encode(file, String.valueOf(System.currentTimeMillis())));
                Files.copy(cicdcFile.getInputStream(), Paths.get(imageDir, corporationVO.getContactIdentificationCardDownCopy()));
            } catch (IOException e) {
                LOGGER.error("上传企业联系人身份证背面出现错误,参数：{}", corporationVO);
            }
        }
        return corporationVO;
    }
}
