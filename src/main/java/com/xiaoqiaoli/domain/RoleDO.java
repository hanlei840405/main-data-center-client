package com.xiaoqiaoli.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Data
public class RoleDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -7181466003493902613L;

    private String code;

    private String name;

    private List<AccountDO> accounts;
}
