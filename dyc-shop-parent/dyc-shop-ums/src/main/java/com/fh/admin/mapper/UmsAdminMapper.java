package com.fh.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.admin.entity.UmsAdmin;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author diao
 * @since 2021-05-11
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    IPage<UmsAdmin> getUsAList(Page<UmsAdmin> page);
}
