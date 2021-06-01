package com.fh.menus.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.menus.entity.UmsMenus;
import com.fh.menus.service.IUmsMenusService;
import com.fh.permission.entity.UmsPermission;
import com.fh.result.ResultObject;
import com.fh.search.MenusSearch;
import com.fh.search.PermissionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 后台权限表 前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-15
 */
@RestController
@RequestMapping("/menus/ums-menus")
public class UmsMenusController {

    @Autowired
    private IUmsMenusService    iumsMenusService;


    @GetMapping
    public IPage<UmsMenus> queryPageMenus(MenusSearch menus){
        QueryWrapper<UmsMenus> queryWrapper=new QueryWrapper<UmsMenus>();
        queryWrapper.like("pid",menus.getPid());
        queryWrapper.orderByDesc("sort");
        return iumsMenusService.page(menus,queryWrapper);
    }


    @PostMapping
    public ResultObject saveOrUpdateMenus(@RequestBody UmsMenus menus){
        return iumsMenusService.saveOrUpdateMenus(menus);
    }

    @DeleteMapping
    public ResultObject deleteMenus(@RequestBody String ids){
        List<Long> longs = JSONArray.parseArray(ids, Long.class);
        iumsMenusService.removeByIds(longs);
        return ResultObject.success();
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        iumsMenusService.removeById(id);
        return ResultObject.success();
    }



    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        UmsMenus byId = iumsMenusService.getById(id);
        return ResultObject.success(byId);
    }

    @GetMapping("/role/{pid}")
    public ResultObject getByPid(@PathVariable("pid") Long pid){
        QueryWrapper<UmsMenus> umsMenusQueryWrapper = new QueryWrapper<UmsMenus>();
        if (pid!=null &&  pid !=0){
            umsMenusQueryWrapper.notIn("pid",0l);
        }else {
            umsMenusQueryWrapper.eq("pid",0l);
        }
        umsMenusQueryWrapper.select("id,name,pid");
        List<UmsMenus> list = iumsMenusService.list(umsMenusQueryWrapper);
        return ResultObject.success(list);
    }

    @GetMapping("tree")
    public ResultObject queryMenusZtreeList(){
        return iumsMenusService.queryMenusZtreeList();
    }


}
