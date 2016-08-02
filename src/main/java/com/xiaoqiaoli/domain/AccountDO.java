package com.xiaoqiaoli.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Data
public class AccountDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -5620076148355923176L;

    private String username;

    private String password;

    private UserDO user;
}
