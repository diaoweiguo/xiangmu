package com.fh.roleandpermission.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.bo.RoleAndPermission;
import com.fh.result.ResultObject;
import com.fh.roleandpermission.entity.UmsRolePermission;
import com.fh.roleandpermission.service.IUmsRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/ums/roleAndPermission")
public class UmsRolePermissionController {

    @Autowired
    private IUmsRolePermissionService    iumsRolePermissionService;

    @PostMapping
    public ResultObject  saveRoleAndPermission(@RequestBody RoleAndPermission roleAndPermission){
      return   iumsRolePermissionService.saveRoleAndPermission(roleAndPermission);
    }

    @GetMapping("{roleId}")
    public ResultObject  queryRoleById(@PathVariable("roleId") Long roleId){
        QueryWrapper<UmsRolePermission> umsRolePermissionQueryWrapper = new QueryWrapper<UmsRolePermission>();
        umsRolePermissionQueryWrapper.eq("roleId",roleId);
        List<UmsRolePermission> list = iumsRolePermissionService.list(umsRolePermissionQueryWrapper);
        return ResultObject.success(list);
    }

}
