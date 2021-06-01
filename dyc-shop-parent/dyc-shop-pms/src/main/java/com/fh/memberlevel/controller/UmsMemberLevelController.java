package com.fh.memberlevel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.memberlevel.entity.UmsMemberLevel;
import com.fh.memberlevel.service.IUmsMemberLevelService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-25
 */
@RestController
@RequestMapping("/psm/memberlevel")
public class UmsMemberLevelController {

    @Autowired
    private IUmsMemberLevelService  iumsMemberLevelService;


    @GetMapping
    public ResultObject  queryMemBerLevelList(){
        QueryWrapper<UmsMemberLevel> umsMemberLevelQueryWrapper = new QueryWrapper<>();
        umsMemberLevelQueryWrapper.eq("default_status",0);
        umsMemberLevelQueryWrapper.orderByAsc("growth_point");
        List<UmsMemberLevel> list = iumsMemberLevelService.list(umsMemberLevelQueryWrapper);
       return ResultObject.success(list);
    }


}
