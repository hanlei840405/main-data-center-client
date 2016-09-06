package com.xiaoqiaoli.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Entity(name = "mdc_account")
@Setter
@Getter
public class Account extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5620076148355923176L;

    private String username;

    private String password;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "account")
    private User user;

    @ManyToMany(mappedBy = "accounts")
    private Set<Role> roles = new HashSet<>();
}
