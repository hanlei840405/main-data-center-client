package com.xiaoqiaoli.model;

import com.xiaoqiaoli.entity.Organization;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by hanlei6 on 2016/9/1.
 */

@StaticMetamodel(Organization.class)
public class Organization_ {

    public static volatile SingularAttribute<Organization, Organization> organization;
}
