package com.fh.adminandrole.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.adminandrole.entity.UmsAdminRole;
import com.fh.bo.AdMinAndRoleBo;
import com.fh.result.ResultObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
public interface IUmsAdminRoleService extends IService<UmsAdminRole> {

    ResultObject saveAdminAndRole(AdMinAndRoleBo andRoleBo);
}
