package com.fh.adminandmenus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.adminandmenus.entity.UmsAdminMenus;
import com.fh.adminandmenus.mapper.UmsAdminMenusMapper;
import com.fh.adminandmenus.service.IUmsAdminMenusService;
import com.fh.adminandrole.entity.UmsAdminRole;
import com.fh.bo.AdminAndMenusBo;
import com.fh.result.ResultObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-17
 */
@Service
public class UmsAdminMenusServiceImpl extends ServiceImpl<UmsAdminMenusMapper, UmsAdminMenus> implements IUmsAdminMenusService {

    @Override
    public ResultObject saveAdminAndMenus(AdminAndMenusBo adminAndMenusBo) {
        QueryWrapper<UmsAdminMenus> queryWrapper = new QueryWrapper<UmsAdminMenus>();
        queryWrapper.eq("adminId",adminAndMenusBo.getUserId());
        remove(queryWrapper);
        if (!CollectionUtils.isEmpty(adminAndMenusBo.getMenusIdList())){
            List<UmsAdminMenus> list=new ArrayList<UmsAdminMenus>();
            adminAndMenusBo.getMenusIdList().forEach(menusId->{
                UmsAdminMenus umsAdminMenus = new UmsAdminMenus();
                umsAdminMenus.setAdminid(adminAndMenusBo.getUserId());
                umsAdminMenus.setMenusid(menusId);
                list.add(umsAdminMenus);
            });
            saveBatch(list);
        }
        return ResultObject.success("添加菜单成功");
    }
}
