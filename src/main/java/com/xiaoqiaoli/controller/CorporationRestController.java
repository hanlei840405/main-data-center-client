package com.xiaoqiaoli.controller;

import com.github.pagehelper.Page;
import com.xiaoqiaoli.entity.Corporation;
import com.xiaoqiaoli.service.CorporationLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hanlei6 on 2016/8/19.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/corporation")
public class CorporationRestController {

    @Autowired
    private CorporationLocalService corporationService;

    @RequestMapping("/page")
    public List<Corporation> page(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, Corporation corporation) {
        Page<Corporation> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return corporationService.localPage(page, corporation.getName(), corporation.getContact(), corporation.getLegalPerson());
    }
}
