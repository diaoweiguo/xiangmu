package com.fh.adminandrole.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.adminandrole.entity.UmsAdminRole;
import com.fh.adminandrole.service.IUmsAdminRoleService;
import com.fh.bo.AdMinAndRoleBo;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/ums/adminRole")
public class UmsAdminRoleController {

    @Autowired
    private IUmsAdminRoleService  iumsAdminRoleService;



    @PostMapping
    public ResultObject saveAdminAndRole(@RequestBody AdMinAndRoleBo andRoleBo){
        return iumsAdminRoleService.saveAdminAndRole(andRoleBo);
    }

    @GetMapping("{userId}")
    public ResultObject selectRoleListById(@PathVariable("userId") Long userId){
        QueryWrapper<UmsAdminRole> umsAdminRoleQueryWrapper = new QueryWrapper<>();
        umsAdminRoleQueryWrapper.eq("adminId",userId);
        umsAdminRoleQueryWrapper.select("roleId");
        List<UmsAdminRole> list = iumsAdminRoleService.list(umsAdminRoleQueryWrapper);
        List<Long> roleIdList=list.stream().map(x->x.getRoleid()).collect(Collectors.toList());
        return ResultObject.success(roleIdList);
    }



}
