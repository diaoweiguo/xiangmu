package com.fh.productattributecategory.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.po.RolePo;
import com.fh.productattributecategory.entity.PmsProductAttributeCategory;
import com.fh.productattributecategory.service.IPmsProductAttributeCategoryService;
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
 * @since 2021-05-21
 */
@RestController
@RequestMapping("/pms/productattributecategory")
public class PmsProductAttributeCategoryController {

    @Autowired
    private IPmsProductAttributeCategoryService  ipmsProductAttributeCategoryService;


    @GetMapping
    public IPage<PmsProductAttributeCategory> queryPageAttrCate(Page<PmsProductAttributeCategory> page){
        QueryWrapper<PmsProductAttributeCategory> queryWrapper=new QueryWrapper<PmsProductAttributeCategory>();
        queryWrapper.orderByDesc("id");
        return ipmsProductAttributeCategoryService.page(page);
    }


    @PostMapping
    public ResultObject saveOrUpdateAttrCate(@RequestBody PmsProductAttributeCategory productAttributeCategory){
        ipmsProductAttributeCategoryService.saveOrUpdate(productAttributeCategory);
        return  ResultObject.success();
    }

    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        PmsProductAttributeCategory byId = ipmsProductAttributeCategoryService.getById(id);
        return ResultObject.success(byId);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        ipmsProductAttributeCategoryService.removeById(id);
        return ResultObject.success();
    }

    @GetMapping("select")
    public ResultObject selectAttrCeteList(){
        QueryWrapper<PmsProductAttributeCategory> queryWrapper=new QueryWrapper<PmsProductAttributeCategory>();
        queryWrapper.select("id,name");
        List<PmsProductAttributeCategory> list = ipmsProductAttributeCategoryService.list(queryWrapper);
        return ResultObject.success(list);
    }

}
