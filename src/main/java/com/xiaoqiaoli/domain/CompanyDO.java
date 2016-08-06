package com.xiaoqiaoli.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hanlei6 on 2016/8/6.
 */
@Data
public class CompanyDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 5508727728302380626L;

    private String name;

    private String address;

    private String corporation;
}
