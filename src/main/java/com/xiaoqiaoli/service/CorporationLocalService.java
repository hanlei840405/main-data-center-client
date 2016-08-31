package com.xiaoqiaoli.service;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.entity.Corporation;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public interface CorporationLocalService {
    Corporation localGet(String id);

    List<Corporation> localFindByName(String name);

    List<Corporation> localFindByContact(String contact);

    List<Corporation> localFindByLegalPerson(String legalPerson);

    Page<Corporation> localPage(Page<Corporation> page, String name, String contact, String legalPerson);

    Corporation insert(Corporation corporationDO);

    Corporation update(Corporation corporationDO);

    int batchEnable(String[] ids);

    int batchDisable(String[] ids);

    int delete(String id);

    int batchDelete(String[] ids);

    int adjust(Corporation corporationDO);
}
