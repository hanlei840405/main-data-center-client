package com.xiaoqiaoli.service;

import com.xiaoqiaoli.entity.Corporation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public interface CorporationLocalService {
    Corporation localGet(String id);

    List<Corporation> localFindByIds(String[] ids);

    List<Corporation> localFindByName(String name);

    List<Corporation> localFindByContact(String contact);

    List<Corporation> localFindByLegalPerson(String legalPerson);

    Page<Corporation> localPage(Pageable pageable, String name, String legalPerson, String contact);

    Corporation insert(Corporation corporation);

    Corporation update(Corporation corporation);

    void batchEnable(List<Corporation> corporations);

    void batchDisable(List<Corporation> corporations);

    Corporation delete(Corporation corporation);

    void batchDelete(List<Corporation> corporations);

    Corporation adjust(Corporation corporation);
}
