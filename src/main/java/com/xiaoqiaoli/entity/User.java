package com.xiaoqiaoli.entity;

import com.xiaoqiaoli.enums.Level;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hanlei6 on 2016/7/14.
 */
@Entity(name = "mdc_user")
@Setter
@Getter
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8002151724019827980L;

    private String realName;

    private String gender;

    private Date birthday;

    private String telephone;

    private String email;

    private String qq;

    private String wx;

    private String weiBo;

    private String photo;

    private Level level;

    private Corporation corporation;

    private Organization organization;
}