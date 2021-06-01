package com.fh.adminandmenus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.adminandmenus.entity.UmsAdminMenus;
import com.fh.bo.AdminAndMenusBo;
import com.fh.result.ResultObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-17
 */
public interface IUmsAdminMenusService extends IService<UmsAdminMenus> {

    ResultObject saveAdminAndMenus(AdminAndMenusBo adminAndMenusBo);
}
