package com.fh.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.permission.entity.UmsPermission;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    List<String> getPermissionByUserId(Long userId);
}
