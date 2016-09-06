package com.xiaoqiaoli.model;

import com.xiaoqiaoli.entity.Corporation;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by hanlei6 on 2016/9/1.
 */

@StaticMetamodel(Corporation.class)
public class Corporation_ {

    public static volatile SingularAttribute<Corporation, String> name;
    public static volatile SingularAttribute<Corporation, String> contact;
    public static volatile SingularAttribute<Corporation, String> legalPerson;
}
