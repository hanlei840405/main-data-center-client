package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface CorporationRepository extends JpaRepository<Corporation, String>, PagingAndSortingRepository<Corporation, String> {
//
//    int batchEnable(Map<String, Object> params);
//
//    int batchDisable(Map<String, Object> params);
//
//    int adjust(Corporation corporationDO);

    List<Corporation> findByNameLike(String name);

    List<Corporation> findByContactLike(String contact);

    List<Corporation> findByLegalPersonLike(String legalPerson);

    List<Corporation> findByIdIn(String[] ids);
}
