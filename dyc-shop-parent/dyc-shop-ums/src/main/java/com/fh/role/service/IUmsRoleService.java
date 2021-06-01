package com.fh.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.result.ResultObject;
import com.fh.role.entity.UmsRole;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
public interface IUmsRoleService extends IService<UmsRole> {

    ResultObject saveOrUpdateRole(UmsRole role);

    List<String> getRoleByUserId(Long userId);
}
