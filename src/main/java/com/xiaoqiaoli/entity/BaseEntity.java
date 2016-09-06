package com.xiaoqiaoli.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@MappedSuperclass
@Setter
@Getter
public class BaseEntity implements Serializable {
    @Id
    private String id;

    private String status;

    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account creator;

    private Date modified;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account modifier;

    @Version
    private int version;
}
