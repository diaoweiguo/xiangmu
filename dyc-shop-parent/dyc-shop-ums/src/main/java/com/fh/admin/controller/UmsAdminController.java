package com.fh.admin.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.admin.entity.UmsAdmin;
import com.fh.admin.service.IUmsAdminService;
import com.fh.po.UserPo;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/ums/admin")
public class UmsAdminController {


    @Autowired
    private IUmsAdminService iumsAdminService;



   /*@PreAuthorize("hasAuthority('wwwwwwwwwwww')")*/
    /*@Secured()*/
   @GetMapping
   public IPage<UmsAdmin> queryPageUser(Page<UmsAdmin> page,UserPo user){
       QueryWrapper<UmsAdmin> queryWrapper=new QueryWrapper<UmsAdmin>();

       if(StringUtils.isNotBlank(user.getNickName())){
           queryWrapper.like("username",user.getNickName());
       }
       queryWrapper.orderByDesc("create_time");
       return iumsAdminService.page(page,queryWrapper);
   }


    @DeleteMapping
    public ResultObject deleteAdmin(@RequestBody String ids){
        List<Long> longs = JSONArray.parseArray(ids, Long.class);
        iumsAdminService.removeByIds(longs);
        return ResultObject.success();
    }

    @PostMapping
    public ResultObject saveOrUpdateUser(@RequestBody UmsAdmin user){
       return iumsAdminService.saveOrUpdateUser(user);
    }

    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        UmsAdmin byId = iumsAdminService.getById(id);
        return ResultObject.success(byId);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        iumsAdminService.removeById(id);
        return ResultObject.success();
    }


}
