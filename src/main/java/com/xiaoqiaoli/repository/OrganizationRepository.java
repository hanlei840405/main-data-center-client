package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String>, PagingAndSortingRepository<Organization, String> {
}
