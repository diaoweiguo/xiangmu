package com.fh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.admin.entity.UmsAdmin;
import com.fh.admin.mapper.UmsAdminMapper;
import com.fh.admin.service.IUmsAdminService;
import com.fh.md5.MD5Util;
import com.fh.po.UserPo;
import com.fh.result.ResultObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Wrapper;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-11
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Override
    public IPage<UmsAdmin> getUsAList(Page<UmsAdmin> page, UserPo user) {

        return umsAdminMapper.getUsAList(page);
    }

    @Override
    public ResultObject saveOrUpdateUser(UmsAdmin user) {
        if(user.getId() == null){
            user.setCreateTime(new Date());
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encode = bCryptPasswordEncoder.encode("123456");
            user.setPassword(encode);
        }
        saveOrUpdate(user);
        return ResultObject.success();
    }

    @Override
    public UmsAdmin getUserByName(String username) {
        QueryWrapper<UmsAdmin> umsAdminQueryWrapper = new QueryWrapper<>();
        umsAdminQueryWrapper.eq("username",username);
        List<UmsAdmin> list = list(umsAdminQueryWrapper);
        if (CollectionUtils.isEmpty(list)){
            return null;
        }else {
            UmsAdmin umsAdmin = list.get(0);
            return umsAdmin;
        }
    }
}
