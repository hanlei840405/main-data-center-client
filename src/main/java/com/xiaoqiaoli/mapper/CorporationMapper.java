package com.xiaoqiaoli.mapper;

import com.xiaoqiaoli.domain.CorporationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Mapper
public interface CorporationMapper extends BaseMapper<CorporationDO, String> {
    List<CorporationDO> findByMultiIds(@Param("ids") String[] ids);

    int batchEnable(@Param("corporations") List<CorporationDO> corporationDOs, @Param("modifier") String modifier);

    int batchDisable(@Param("corporations") List<CorporationDO> corporationDOs, @Param("modifier") String modifier);

    int adjust(@Param("corporation") CorporationDO corporationDO);

}
