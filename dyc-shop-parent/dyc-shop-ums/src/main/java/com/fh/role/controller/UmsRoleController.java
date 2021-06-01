package com.fh.role.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.admin.entity.UmsAdmin;
import com.fh.po.RolePo;
import com.fh.po.UserPo;
import com.fh.result.ResultObject;
import com.fh.role.entity.UmsRole;
import com.fh.role.service.IUmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/role/ums-role")
public class UmsRoleController {

    @Autowired
    private IUmsRoleService iumsRoleService;


    @GetMapping
    public IPage<UmsRole> queryPageRole(Page<UmsRole> page, RolePo role){
        QueryWrapper<UmsRole> queryWrapper=new QueryWrapper<UmsRole>();
        if(StringUtils.isNotBlank(role.getName())){
            queryWrapper.like("name",role.getName());
        }
        queryWrapper.orderByDesc("create_time");
        return iumsRoleService.page(page,queryWrapper);
    }


    @DeleteMapping
    public ResultObject deleteRole(@RequestBody String ids){
        List<Long> longs = JSONArray.parseArray(ids, Long.class);
        iumsRoleService.removeByIds(longs);
        return ResultObject.success();
    }

    @PostMapping
    public ResultObject saveOrUpdateRole(@RequestBody UmsRole role){
        return iumsRoleService.saveOrUpdateRole(role);
    }

    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        UmsRole byId = iumsRoleService.getById(id);
        return ResultObject.success(byId);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        iumsRoleService.removeById(id);
        return ResultObject.success();
    }

    @GetMapping("select")
    public ResultObject selectRoleList (){
        QueryWrapper<UmsRole> umsRoleQueryWrapper = new QueryWrapper<>();
        umsRoleQueryWrapper.select("id,name");
        List<UmsRole> list = iumsRoleService.list(umsRoleQueryWrapper);
        return ResultObject.success(list);
    }





}
