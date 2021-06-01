package com.fh.brand.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.brand.entity.PmsBrand;
import com.fh.brand.service.IPmsBrandService;
import com.fh.po.BrandPo;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-20
 */
@RestController
@RequestMapping("/brand/pms-brand")
public class PmsBrandController {

    @Autowired
    private IPmsBrandService  ipmsBrandService;

    @GetMapping
    public IPage<PmsBrand> queryPageBrand(Page<PmsBrand> page, BrandPo brand){
        QueryWrapper<PmsBrand> queryWrapper=new QueryWrapper<PmsBrand>();

        if(StringUtils.isNotBlank(brand.getName())){
            queryWrapper.like("name",brand.getName());
        }
        queryWrapper.orderByDesc("id");
        return ipmsBrandService.page(page,queryWrapper);
    }

    @DeleteMapping
    public ResultObject deleteAdmin(@RequestBody String ids){
        List<Long> longs = JSONArray.parseArray(ids, Long.class);
        ipmsBrandService.removeByIds(longs);
        return ResultObject.success();
    }

   @PostMapping
    public ResultObject saveOrUpdateBrand(@RequestBody PmsBrand pmsBrand){
        ipmsBrandService.saveOrUpdate(pmsBrand);
        return ResultObject.success();
    }

    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        PmsBrand byId = ipmsBrandService.getById(id);
        return ResultObject.success(byId);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        ipmsBrandService.removeById(id);
        return ResultObject.success();
    }

    @GetMapping("CategorySelectList")
    public ResultObject queryBrandSelectList(){
        QueryWrapper<PmsBrand> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.orderByDesc("sort");
        pmsProductCategoryQueryWrapper.select("id,name");
        List<PmsBrand> list = ipmsBrandService.list(pmsProductCategoryQueryWrapper);
        return ResultObject.success(list);
    }


}
