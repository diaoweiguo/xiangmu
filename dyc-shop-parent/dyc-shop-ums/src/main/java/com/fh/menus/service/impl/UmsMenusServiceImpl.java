package com.fh.menus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.menus.entity.UmsMenus;
import com.fh.menus.mapper.UmsMenusMapper;
import com.fh.menus.service.IUmsMenusService;
import com.fh.result.ResultObject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 后台权限表 服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-15
 */
@Service
public class UmsMenusServiceImpl extends ServiceImpl<UmsMenusMapper, UmsMenus> implements IUmsMenusService {

    @Override
    public ResultObject saveOrUpdateMenus(UmsMenus menus) {
        if(menus.getId() == null){
            menus.setCreateTime(new Date());
        }
        if (menus.getPid()==null){
            menus.setPid(0l);
        }
        saveOrUpdate(menus);
        return ResultObject.success();
    }

    @Override
    public ResultObject queryMenusZtreeList() {
        List<UmsMenus> list = list();
        List<Map<String, Object>> menusList = getTree(list, 0l);
        return ResultObject.success(menusList);
    }

    private List<Map<String, Object>> getTree(List<UmsMenus> menusList, Long pid) {
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        menusList.forEach(menus->{
            Map<String,Object> map=null;
            if (pid.equals(menus.getPid())){
                map=new HashMap<>();
                map.put("id",menus.getId());
                map.put("label",menus.getName());
                map.put("children",getTree(menusList,menus.getId()));
            }
            if (map!=null){
                list.add(map);
            }
        });
        return list;
    }

}
