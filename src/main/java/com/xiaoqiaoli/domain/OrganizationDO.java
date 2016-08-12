package com.xiaoqiaoli.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hanlei6 on 2016/8/6.
 */
@Data
public class OrganizationDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -777339030089331149L;
    private String code;

    private String name;

    private String fullCode;

    private String fullName;

    private int level;

    private CorporationDO corporation;

    private OrganizationDO organization;

    private UserDO user;
}
