package com.xiaoqiaoli.service;

import com.xiaoqiaoli.vo.CorporationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
public interface CorporationLocalService {
    CorporationVO localGet(String id);

    List<CorporationVO> localFindByIds(String[] ids);

    List<CorporationVO> localFindByName(String name);

    List<CorporationVO> localFindByContact(String contact);

    List<CorporationVO> localFindByLegalPerson(String legalPerson);

    Page<CorporationVO> localPage(Pageable pageable, String name, String legalPerson, String contact);

    CorporationVO insert(CorporationVO CorporationVO);

    CorporationVO update(CorporationVO CorporationVO);

    void batchEnable(List<CorporationVO> CorporationVOs);

    void batchDisable(List<CorporationVO> CorporationVOs);

    CorporationVO delete(CorporationVO CorporationVO);

    void batchDelete(List<CorporationVO> CorporationVOs);

    CorporationVO adjust(CorporationVO CorporationVO);
}
