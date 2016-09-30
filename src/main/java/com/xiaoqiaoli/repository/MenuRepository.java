package com.xiaoqiaoli.repository;

import com.xiaoqiaoli.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hanlei6 on 2016/7/15.
 */
@Repository
public interface MenuRepository extends BaseRepository<Menu, String> {
    Menu findByCode(String code);

    List<Menu> findByLevel(int level);

    List<Menu> findByApplicationId(String id);

    List<Menu> findByMenuId(String id);
}
