package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Role;
import com.xiaoqiaoli.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by hanlei6 on 2016/7/19.
 */
@Component
public class RoleManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(RoleManager.class);
    @Autowired
    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${batch.size}")
    private int batchSize;

    public Role get(String id) {
        return roleRepository.findOne(id);
    }

    /**
     * 根据code查询角色信息
     *
     * @param code
     * @return
     */
    public Role getByCode(String code) {
        return roleRepository.findTopByCode(code);
    }

    public List<Role> findByIds(String[] ids) {
        return roleRepository.findByIdIn(ids);
    }

    /**
     * 根据账号查询用户角色信息
     *
     * @param username
     * @return
     */
    public List<Role> findByUsername(String username) {
        return roleRepository.findByUsername(username);
    }

    /**
     * 与账号关联
     *
     * @param role
     * @return
     */
    public Role connectAccount(Role role) {
        return roleRepository.save(role);
    }

    /**
     * 与账号解绑
     *
     * @param role
     * @return
     */
    public Role disConnectAccount(Role role) {
        return roleRepository.save(role);
    }

    public Role insert(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public Role delete(Role role) {
        return roleRepository.save(role);
    }

    public void batch(List<Role> roles) {

        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            entityManager.merge(role);
            if (i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
