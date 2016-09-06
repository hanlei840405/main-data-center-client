package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Corporation;
import com.xiaoqiaoli.model.Corporation_;
import com.xiaoqiaoli.repository.CorporationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by hanlei6 on 2016/8/7.
 */
@Component
public class CorporationManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(CorporationManager.class);

    @Autowired
    private CorporationRepository corporationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${batch.size}")
    private int batchSize;

    /**
     * 根据id查询企业信息
     *
     * @param id
     * @return
     */
    public Corporation get(String id) {
        return corporationRepository.findOne(id);
    }

    public List<Corporation> findByIds(String[] ids) {
        return corporationRepository.findByIdIn(ids);
    }

    /**
     * 根据企业名称查询入驻企业信息
     *
     * @param name
     * @return
     */
    public List<Corporation> findByName(String name) {
        return corporationRepository.findByNameLike(name);
    }

    /**
     * 根据联系人查询入驻企业信息
     *
     * @param contact
     * @return
     */
    public List<Corporation> findByContact(String contact) {
        return corporationRepository.findByContactLike(contact);
    }

    /**
     * 根据法人查询入驻企业信息
     *
     * @param legalPerson
     * @return
     */
    public List<Corporation> findByLegalPerson(String legalPerson) {
        return corporationRepository.findByLegalPersonLike(legalPerson);
    }


    public Corporation save(Corporation corporation) {
        return corporationRepository.save(corporation);
    }

    @Transactional
    public void batch(List<Corporation> corporations) {
        for (int i = 0; i < corporations.size(); i++) {
            Corporation corporation = corporations.get(i);
            entityManager.merge(corporation);
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    public Page<Corporation> page(Pageable pageable, String name, String legalPerson, String contact) {
        Page<Corporation> page = corporationRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (!StringUtils.isEmpty(name)) {
                predicate.getExpressions().add(cb.like(root.get(Corporation_.name), "%".concat(name).concat("%")));
            }
            if (!StringUtils.isEmpty(legalPerson)) {
                predicate.getExpressions().add(cb.like(root.get(Corporation_.legalPerson), "%".concat(legalPerson).concat("%")));
            }
            if (!StringUtils.isEmpty(contact)) {
                predicate.getExpressions().add(cb.like(root.get(Corporation_.contact), "%".concat(contact).concat("%")));
            }
            return predicate;
        }, pageable);
        return page;
    }
}
