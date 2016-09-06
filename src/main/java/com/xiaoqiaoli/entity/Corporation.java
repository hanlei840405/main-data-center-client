package com.xiaoqiaoli.entity;

import com.xiaoqiaoli.enums.Level;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hanlei6 on 2016/8/6.
 */
@Entity(name = "mdc_corporation")
@Setter
@Getter
public class Corporation extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5508727728302380626L;

    private String category; // 0:个人, 1:企业

    private String name; // 名称

    private String tel; // 企业电话

    private String contact; // 企业联系人

    private String contactTelephone; // 企业联系人电话

    private String legalPerson; // 法人

    private String legalPersonTelephone; // 法人电话

    private String country; // 国家

    private String countryCode; // 国家编号

    private String province; // 省,自治区,直辖市

    private String provinceCode; // 省,自治区,直辖市编号

    private String city; // 市

    private String cityCode; //市编号

    private String district; // 区

    private String districtCode; // 区编号

    private String address; // 企业地址

    private String contactIdNumber; // 企业联系人身份证号

    private String legalPersonIdNumber; // 法人身份证号

    private String businessLicence; // 营业执照号码

    private String taxRegistrationCertificate; // 税务登记号码

    private String organizationCodeCertificate; // 组织机构代码证

    private String accountOpeningLicense; // 银行开户证

    private String logo; // 企业LOGO

    private String businessLicenceCopy; // 营业执照副本

    private String taxRegistrationCertificateCopy; // 税务登记号副本

    private String organizationCodeCertificateCopy; // 组织机构代码证副本

    private String accountOpeningLicenseCopy; // 银行开户副本

    private String legalPersonIdentificationCardUpCopy; // 法人身份证正面

    private String legalPersonIdentificationCardDownCopy; // 法人身份证背面

    private String contactIdentificationCardUpCopy; // 企业联系人身份证正面

    private String contactIdentificationCardDownCopy; // 企业联系人身份证背面

    private Level level; // 等级,A:普通会员,B:铜牌会员,C:银牌会员,D:金牌会员

    @OneToMany(mappedBy = "corporation", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<Organization> organizations = new HashSet<>(0);

    @OneToMany(mappedBy = "corporation", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>(0);
}
