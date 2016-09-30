package com.xiaoqiaoli.entity;

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
 * Created by hanlei6 on 2016/9/29.
 */
@Entity(name = "mdc_application")
@Setter
@Getter
public class Application extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 630405676254883438L;

    private String name;

    @OneToMany(mappedBy = "application", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<Menu> menus = new HashSet<>(0);

}
