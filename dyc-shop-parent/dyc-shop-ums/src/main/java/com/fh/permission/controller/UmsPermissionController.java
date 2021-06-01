package com.fh.permission.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.permission.entity.UmsPermission;
import com.fh.permission.service.IUmsPermissionService;
import com.fh.po.RolePo;
import com.fh.result.ResultObject;
import com.fh.role.entity.UmsRole;
import com.fh.search.PermissionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/permission/ums-permission")
public class UmsPermissionController {

    @Autowired
    private IUmsPermissionService  iumsPermissionService;

    @GetMapping
    public IPage<UmsPermission> queryPagePerm(PermissionSearch perm){
        QueryWrapper<UmsPermission> queryWrapper=new QueryWrapper<UmsPermission>();
            queryWrapper.like("pid",perm.getPid());
        queryWrapper.orderByDesc("sort");
        return iumsPermissionService.page(perm,queryWrapper);
    }


    @PostMapping
    public ResultObject saveOrUpdatePerm(@RequestBody UmsPermission perm){
        return iumsPermissionService.saveOrUpdatePerm(perm);
    }

    @DeleteMapping
    public ResultObject deletePerm(@RequestBody String ids){
        List<Long> longs = JSONArray.parseArray(ids, Long.class);
        iumsPermissionService.removeByIds(longs);
        return ResultObject.success();
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        iumsPermissionService.removeById(id);
        return ResultObject.success();
    }



    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        UmsPermission byId = iumsPermissionService.getById(id);
        return ResultObject.success(byId);
    }

    @GetMapping("/role/{pid}")
    public ResultObject getByPid(@PathVariable("pid") Long pid){
        QueryWrapper<UmsPermission> umsPermissionQueryWrapper = new QueryWrapper<>();
        if (pid!=null &&  pid !=0){
            umsPermissionQueryWrapper.notIn("pid",0l);
        }else {
            umsPermissionQueryWrapper.eq("pid",0l);
        }
        umsPermissionQueryWrapper.select("id,name,pid");
        List<UmsPermission> list = iumsPermissionService.list(umsPermissionQueryWrapper);
        return ResultObject.success(list);
    }


}
