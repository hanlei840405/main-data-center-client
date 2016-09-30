package com.xiaoqiaoli.manager;

import com.xiaoqiaoli.entity.Application;
import com.xiaoqiaoli.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hanlei6 on 2016/9/29.
 */
@Component
public class ApplicationManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationManager.class);

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application get(String id) {
        return applicationRepository.findOne(id);
    }

    public Application save(Application application) {
        return applicationRepository.save(application);
    }
}
