package com.fh.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.admin.entity.UmsAdmin;
import com.fh.po.UserPo;
import com.fh.result.ResultObject;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-11
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    IPage<UmsAdmin> getUsAList(Page<UmsAdmin> page, UserPo user);

    ResultObject saveOrUpdateUser(UmsAdmin user);

    UmsAdmin getUserByName(String username);
}
