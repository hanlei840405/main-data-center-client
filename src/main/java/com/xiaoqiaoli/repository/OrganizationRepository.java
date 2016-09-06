package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Organization;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface OrganizationRepository extends BaseRepository<Organization, String> , JpaSpecificationExecutor {

    Organization findTopByCode(String code);

    List<Organization> findByCorporationId(String corporationId);

    List<Organization> findByOrganizationId(String organizationId);

    List<Organization> findByUserId(String userId);

    List<Organization> findByFullCodeStartingWith(String fullCode);
}
