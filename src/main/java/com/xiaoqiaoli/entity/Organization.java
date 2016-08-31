package com.xiaoqiaoli.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by hanlei6 on 2016/8/6.
 */
@Entity(name = "mdc_organization")
@Setter
@Getter
public class Organization extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -777339030089331149L;
    private String code;

    private String name;

    private String fullCode;

    private String fullName;

    private int level;

    private Corporation corporation;

    private Organization organization;

    private User user;
}
