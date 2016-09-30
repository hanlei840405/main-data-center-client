package com.xiaoqiaoli.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hanlei6 on 2016/9/29.
 */
@Entity(name = "mdc_menu")
@Setter
@Getter
public class Menu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2639050194201574575L;

    private String code;

    private String name;

    private String fullCode;

    private String fullName;

    private int level;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;

    @OneToMany(mappedBy = "menu", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<Menu> menus = new HashSet<>(0);

}
