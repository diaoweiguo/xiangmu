package com.fh.roleandpermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.bo.RoleAndPermission;
import com.fh.result.ResultObject;
import com.fh.roleandpermission.entity.UmsRolePermission;
import com.fh.roleandpermission.mapper.UmsRolePermissionMapper;
import com.fh.roleandpermission.service.IUmsRolePermissionService;
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
 * @since 2021-05-14
 */
@Service
public class UmsRolePermissionServiceImpl extends ServiceImpl<UmsRolePermissionMapper, UmsRolePermission> implements IUmsRolePermissionService {

    @Override
    @Transactional
    public ResultObject saveRoleAndPermission(RoleAndPermission roleAndPermission) {
        QueryWrapper<UmsRolePermission> umsRolePermissionQueryWrapper = new QueryWrapper<>();
        umsRolePermissionQueryWrapper.eq("roleId",roleAndPermission.getRoleId());
        remove(umsRolePermissionQueryWrapper);
        roleAndPermission.getPermList();
        if (!CollectionUtils.isEmpty(roleAndPermission.getPermList())){
            List<UmsRolePermission> list=new ArrayList<UmsRolePermission>();
            roleAndPermission.getPermList().forEach(permId->{
                UmsRolePermission umsRolePermission = new UmsRolePermission();
                umsRolePermission.setRoleid(roleAndPermission.getRoleId());
                umsRolePermission.setPermid(permId);
                list.add(umsRolePermission);
            });
            saveBatch(list);
        }
        return ResultObject.success("资源保存成功");
    }
}
