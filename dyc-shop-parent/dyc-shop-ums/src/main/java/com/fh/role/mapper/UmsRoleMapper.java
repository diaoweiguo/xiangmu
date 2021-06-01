package com.fh.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.role.entity.UmsRole;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author diao
 * @since 2021-05-13
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    List<String> getRoleByUserId(Long userId);
}
