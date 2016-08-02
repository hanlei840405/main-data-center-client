package com.xiaoqiaoli.mapper;

import com.xiaoqiaoli.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO, String> {
    int connectAccount(RoleDO roleDO);

    int disConnectAccount(@Param("roleId") String roleId);
}
