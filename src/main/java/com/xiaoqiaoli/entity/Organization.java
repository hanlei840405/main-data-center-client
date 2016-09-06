package com.xiaoqiaoli.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Corporation corporation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "organization", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<Organization> organizations = new HashSet<>(0);

    @OneToMany(mappedBy = "organization", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>(0);
}
