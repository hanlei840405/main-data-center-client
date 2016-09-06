package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Organization;
import com.xiaoqiaoli.model.Organization_;
import com.xiaoqiaoli.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by hanlei6 on 2016/8/12.
 */
@Component
public class OrganizationManager {
    @Autowired
    private OrganizationRepository organizationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${batch.size}")
    private int batchSize;

    public Organization get(String id) {
        return organizationRepository.findOne(id);
    }

    /**
     * 根据部门code查询
     *
     * @param code
     * @return
     */
    public Organization getByCode(String code) {
        return organizationRepository.findTopByCode(code);
    }

    public List<Organization> findByIds(String[] ids) {
        return organizationRepository.findByIdIn(ids);
    }

    /**
     * 根据企业查询
     *
     * @param corporationId
     * @return
     */
    public List<Organization> findByCorporation(String corporationId) {
        return organizationRepository.findByCorporationId(corporationId);
    }

    /**
     * 根据上级部门code查询
     *
     * @param parentId
     * @return
     */
    public List<Organization> findByParent(String parentId) {
        return organizationRepository.findByOrganizationId(parentId);
    }

    /**
     * 根据负责人账号查询
     *
     * @param username
     * @return
     */
    public List<Organization> findByManager(String username) {
        return organizationRepository.findByUserId(username);
    }

    /**
     * 根据全路径编号查询
     *
     * @param fullCode
     * @return
     */
    public List<Organization> findByFullCode(String fullCode) {
        return organizationRepository.findByFullCodeStartingWith(fullCode);
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public void batch(List<Organization> organizations) {

        for (int i = 0; i < organizations.size(); i++) {
            Organization organization = organizations.get(i);
            entityManager.merge(organization);
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    public Page<Organization> page(Pageable pageable, String parentId) {
        Page<Organization> page = organizationRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (!StringUtils.isEmpty(parentId)) {
                predicate.getExpressions().add(cb.equal(root.get(Organization_.organization).get("id"), parentId));
            }
            return predicate;
        }, pageable);
        return page;
    }
}
