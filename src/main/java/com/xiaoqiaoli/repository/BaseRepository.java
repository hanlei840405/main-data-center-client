package com.xiaoqiaoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hanlei6 on 2016/9/1.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, PagingAndSortingRepository<T, ID> {
    List<T> findByIdIn(ID[] ids);
}
