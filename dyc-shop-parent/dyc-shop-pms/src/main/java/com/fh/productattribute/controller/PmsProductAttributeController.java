package com.fh.productattribute.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.productattribute.entity.PmsProductAttribute;
import com.fh.productattribute.service.IPmsProductAttributeService;
import com.fh.productattributecategory.entity.PmsProductAttributeCategory;
import com.fh.result.ResultObject;
import com.fh.search.PmsProductAttributeSearch;
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
@RequestMapping("/pms/productattribute")
public class PmsProductAttributeController {

    @Autowired
    private IPmsProductAttributeService  ipmsProductAttributeService;

    @GetMapping
    public IPage<PmsProductAttribute> queryPageAttrbute(PmsProductAttributeSearch  productAttributeSearch){
        QueryWrapper<PmsProductAttribute> queryWrapper=new QueryWrapper<PmsProductAttribute>();
        queryWrapper.eq("product_attribute_category_id",productAttributeSearch.getPid());
        queryWrapper.eq("type",productAttributeSearch.getType());
        queryWrapper.orderByDesc("sort");
        return ipmsProductAttributeService.page(productAttributeSearch,queryWrapper);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        ipmsProductAttributeService.removeById(id);
        return ResultObject.success();
    }

    @PostMapping
    public ResultObject saveOrUpdateAttrbute(@RequestBody PmsProductAttribute pmsProductAttribute){
        ipmsProductAttributeService.saveOrUpdate(pmsProductAttribute);
        return  ResultObject.success();
    }

    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        PmsProductAttribute byId = ipmsProductAttributeService.getById(id);
        return ResultObject.success(byId);
    }

    @GetMapping("{AttrbuteId}/{type}")
    public ResultObject findAttributeList(@PathVariable("AttrbuteId") Long AttrbuteId,@PathVariable("type") Integer type){
        QueryWrapper<PmsProductAttribute> pmsProductAttributeQueryWrapper = new QueryWrapper<>();
        pmsProductAttributeQueryWrapper.eq("product_attribute_category_id",AttrbuteId);
        pmsProductAttributeQueryWrapper.eq("type",type);
        List<PmsProductAttribute> list = ipmsProductAttributeService.list(pmsProductAttributeQueryWrapper);
        return ResultObject.success(list);
    }

}
