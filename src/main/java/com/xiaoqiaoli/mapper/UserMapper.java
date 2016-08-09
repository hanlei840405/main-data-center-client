package com.xiaoqiaoli.mapper;

import com.xiaoqiaoli.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO,String> {

    int disConnectRole(@Param("userId") String userId);
}
