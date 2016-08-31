package com.xiaoqiaoli.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Entity(name = "mdc_role")
@Setter
@Getter
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7181466003493902613L;

    private String code;

    private String name;

    private List<Account> accounts;
}
