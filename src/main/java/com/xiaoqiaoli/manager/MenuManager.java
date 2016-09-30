package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Menu;
import com.xiaoqiaoli.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hanlei6 on 2016/9/29.
 */
@Component
public class MenuManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(MenuManager.class);

    @Autowired
    private MenuRepository resourceRepository;

    public Menu get(String id) {
        return resourceRepository.findOne(id);
    }

    public Menu findByCode(String code) {
        return resourceRepository.findByCode(code);
    }

    public List<Menu> findByLevel(int level) {
        return resourceRepository.findByLevel(level);
    }

    public Menu save(Menu menu) {
        return resourceRepository.save(menu);
    }
}
