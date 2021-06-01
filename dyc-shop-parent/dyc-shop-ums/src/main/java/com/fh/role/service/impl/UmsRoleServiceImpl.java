package com.fh.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.md5.MD5Util;
import com.fh.result.ResultObject;
import com.fh.role.entity.UmsRole;
import com.fh.role.mapper.UmsRoleMapper;
import com.fh.role.service.IUmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Autowired
    private  UmsRoleMapper  umsRoleMapper;

    @Override
    public ResultObject saveOrUpdateRole(UmsRole role) {
        if(role.getId() == null){
            role.setCreateTime(new Date());
        }
        saveOrUpdate(role);
        return ResultObject.success();
    }

    @Override
    public List<String> getRoleByUserId(Long userId) {
        return umsRoleMapper.getRoleByUserId(userId);
    }
}
