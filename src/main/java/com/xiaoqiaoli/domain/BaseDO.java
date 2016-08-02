package com.xiaoqiaoli.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Data
public class BaseDO implements Serializable{
    private String id;

    private String status;

    private Date created;

    private String creator;

    private Date modified;

    private String modifier;

    private int version;
}
