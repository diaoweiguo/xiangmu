package com.fh.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.permission.entity.UmsPermission;
import com.fh.result.ResultObject;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
public interface IUmsPermissionService extends IService<UmsPermission> {

    ResultObject saveOrUpdatePerm(UmsPermission perm);

    List<String> getPermissionByUserId(Long userId);
}
