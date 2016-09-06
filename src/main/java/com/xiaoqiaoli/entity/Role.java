package com.xiaoqiaoli.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "mdc_relationship_role_user", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts = new HashSet<>(0);
}
