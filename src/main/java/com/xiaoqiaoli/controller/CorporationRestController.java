package com.xiaoqiaoli.controller;

import com.xiaoqiaoli.service.CorporationLocalService;
import com.xiaoqiaoli.vo.CorporationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<CorporationVO> page(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, CorporationVO corporationVO) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        return corporationService.localPage(pageable, corporationVO.getName(), corporationVO.getContact(), corporationVO.getLegalPerson());
    }
}
