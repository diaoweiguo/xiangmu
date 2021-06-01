package com.fh.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.md5.MD5Util;
import com.fh.permission.entity.UmsPermission;
import com.fh.permission.mapper.UmsPermissionMapper;
import com.fh.permission.service.IUmsPermissionService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements IUmsPermissionService {

    @Autowired
    private  UmsPermissionMapper  umsPermissionMapper;

    @Override
    public ResultObject saveOrUpdatePerm(UmsPermission perm) {
        if(perm.getId() == null){
            perm.setCreateTime(new Date());
        }
        if (perm.getPid()==null){
            perm.setPid(0l);
        }
        saveOrUpdate(perm);
        return ResultObject.success();
    }

    @Override
    public List<String> getPermissionByUserId(Long userId) {
        return umsPermissionMapper.getPermissionByUserId(userId);
    }
}
