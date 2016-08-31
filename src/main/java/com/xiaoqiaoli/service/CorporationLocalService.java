package com.xiaoqiaoli.service;

import com.xiaoqiaoli.entity.Corporation;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public interface CorporationLocalService {
    Corporation localGet(String id);

    List<Corporation> localFindByName(String name);

    List<Corporation> localFindByContact(String contact);

    List<Corporation> localFindByLegalPerson(String legalPerson);

    Page<Corporation> localPage(Page<Corporation> page, String payload);

    Corporation insert(Corporation corporation);

    Corporation update(Corporation corporation);

    int batchEnable(String[] ids);

    int batchDisable(String[] ids);

    int delete(String id);

    int batchDelete(String[] ids);

    int adjust(Corporation corporation);
}
