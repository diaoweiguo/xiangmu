package com.fh.roleandpermission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.bo.RoleAndPermission;
import com.fh.result.ResultObject;
import com.fh.roleandpermission.entity.UmsRolePermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-14
 */
public interface IUmsRolePermissionService extends IService<UmsRolePermission> {

    ResultObject saveRoleAndPermission(RoleAndPermission roleAndPermission);
}
