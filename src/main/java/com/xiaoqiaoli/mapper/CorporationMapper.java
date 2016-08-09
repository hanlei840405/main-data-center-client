package com.xiaoqiaoli.mapper;

import com.xiaoqiaoli.domain.CorporationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Mapper
public interface CorporationMapper extends BaseMapper<CorporationDO, String> {

    int batchEnable(Map<String, Object> params);

    int batchDisable(Map<String, Object> params);

    int adjust(CorporationDO corporationDO);

}
