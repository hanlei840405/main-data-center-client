package com.xiaoqiaoli.mapper;

import com.xiaoqiaoli.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO,String> {
    List<UserDO> findByRealName(@Param("realName") String realName);
}
