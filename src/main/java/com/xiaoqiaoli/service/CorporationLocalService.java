package com.xiaoqiaoli.service;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.domain.CorporationDO;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public interface CorporationLocalService {
    CorporationDO localGet(String id);

    List<CorporationDO> localFindByName(String name);

    List<CorporationDO> localFindByContact(String contact);

    List<CorporationDO> localFindByLegalPerson(String legalPerson);

    Page<CorporationDO> localPage(Page<CorporationDO> page, String name, String contact, String legalPerson);

    CorporationDO insert(CorporationDO corporationDO);

    CorporationDO update(CorporationDO corporationDO);

    int batchEnable(String[] ids);

    int batchDisable(String[] ids);

    int delete(String id);

    int batchDelete(String[] ids);

    int adjust(CorporationDO corporationDO);
}
