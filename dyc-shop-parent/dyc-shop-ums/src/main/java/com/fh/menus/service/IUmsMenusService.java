package com.fh.menus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.menus.entity.UmsMenus;
import com.fh.result.ResultObject;

/**
 * <p>
 * 后台权限表 服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-15
 */
public interface IUmsMenusService extends IService<UmsMenus> {

    ResultObject saveOrUpdateMenus(UmsMenus menus);

    ResultObject queryMenusZtreeList();
}
