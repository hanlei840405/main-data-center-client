package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.User;
import com.xiaoqiaoli.model.Organization_;
import com.xiaoqiaoli.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by hanlei6 on 2016/7/15.
 */
@Component
public class UserManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${batch.size}")
    private int batchSize;

    public User get(String id) {
        return userRepository.findOne(id);
    }

    public User getByEmail(String email) {
        return userRepository.findTopByEmail(email);
    }

    public User getByTelephone(String telephone) {
        return userRepository.findTopByTelephone(telephone);
    }

    public User getByQq(String qq) {
        return userRepository.findTopByQq(qq);
    }

    public User getByWx(String wx) {
        return userRepository.findTopByWx(wx);
    }

    public User getByWeiBo(String weiBo) {
        return userRepository.findTopByWeiBo(weiBo);
    }

    public List<User> findByRealName(String realName) {
        return userRepository.findByRealName(realName);
    }

    public Page<User> page(Pageable pageable, String corporationId, String organizationId) {
        Page<User> page = userRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (!StringUtils.isEmpty(organizationId)) {
                predicate.getExpressions().add(cb.equal(root.get(Organization_.organization).get("id"), organizationId));
            } else {
                predicate.getExpressions().add(cb.equal(root.get(Organization_.organization).get("id"), corporationId));
            }
            return predicate;
        }, pageable);
        return page;
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User delete(User user) {
        return userRepository.save(user);
    }

    public void batch(List<User> roles) {

        for (int i = 0; i < roles.size(); i++) {
            User user = roles.get(i);
            entityManager.merge(user);
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    public List<User> findByIds(String[] ids) {
        return userRepository.findByIdIn(ids);
    }
}
