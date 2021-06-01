package com.fh.adminandmenus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.adminandmenus.entity.UmsAdminMenus;
import com.fh.adminandmenus.service.IUmsAdminMenusService;
import com.fh.bo.AdminAndMenusBo;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-17
 */
@RestController
@RequestMapping("/ums/adminAndMenus")
public class UmsAdminMenusController {

    @Autowired
    private IUmsAdminMenusService   iumsAdminMenusService;

    @PostMapping
    public ResultObject saveAdminAndMenus(@RequestBody AdminAndMenusBo adminAndMenusBo){
        return iumsAdminMenusService.saveAdminAndMenus(adminAndMenusBo);
    }

    @GetMapping("{userId}")
    public  ResultObject  queryAdminAndMenusList(@PathVariable("userId") Long userId){
        QueryWrapper<UmsAdminMenus> umsAdminMenusQueryWrapper = new QueryWrapper<>();
        umsAdminMenusQueryWrapper.eq("adminId",userId);
        List<UmsAdminMenus> list = iumsAdminMenusService.list(umsAdminMenusQueryWrapper);
        return ResultObject.success(list);
    }


}
