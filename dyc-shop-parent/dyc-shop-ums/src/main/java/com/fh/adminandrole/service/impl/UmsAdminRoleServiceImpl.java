package com.fh.adminandrole.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.adminandrole.entity.UmsAdminRole;
import com.fh.adminandrole.mapper.UmsAdminRoleMapper;
import com.fh.adminandrole.service.IUmsAdminRoleService;
import com.fh.bo.AdMinAndRoleBo;
import com.fh.result.ResultObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
@Service
public class UmsAdminRoleServiceImpl extends ServiceImpl<UmsAdminRoleMapper, UmsAdminRole> implements IUmsAdminRoleService {
    @Override
    @Transactional
    public ResultObject saveAdminAndRole(AdMinAndRoleBo andRoleBo) {
        QueryWrapper<UmsAdminRole> queryWrapper = new QueryWrapper<UmsAdminRole>();
        queryWrapper.eq("adminId",andRoleBo.getUserId());
           remove(queryWrapper);

        if (!CollectionUtils.isEmpty(andRoleBo.getRoleList())){
            List<UmsAdminRole> list=new ArrayList<UmsAdminRole>();
            andRoleBo.getRoleList().forEach(roleId->{
                UmsAdminRole umsAdminRole = new UmsAdminRole();
                umsAdminRole.setAdminid(andRoleBo.getUserId());
                umsAdminRole.setRoleid(roleId);
                list.add(umsAdminRole);
            });
          saveBatch(list);
        }
        return ResultObject.success("添加角色成功");
    }



}
