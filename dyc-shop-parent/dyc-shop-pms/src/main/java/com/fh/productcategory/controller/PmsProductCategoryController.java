package com.fh.productcategory.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fh.productattribute.entity.PmsProductAttribute;
import com.fh.productcategory.entity.PmsProductCategory;
import com.fh.productcategory.service.IPmsProductCategoryService;
import com.fh.result.ResultObject;
import com.fh.search.ProductCategorySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/pms/productcategory")
public class PmsProductCategoryController {


    @Autowired
    private IPmsProductCategoryService  ipmsProductCategoryService;


    @GetMapping
    public IPage<PmsProductCategory> queryCateList(ProductCategorySearch  productCategorySearch){
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.eq("parent_id",productCategorySearch.getPid());
        pmsProductCategoryQueryWrapper.orderByDesc("sort");
        return ipmsProductCategoryService.page(productCategorySearch,pmsProductCategoryQueryWrapper);
    }

    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        ipmsProductCategoryService.removeById(id);
        return ResultObject.success();
    }

    @PostMapping
    public ResultObject saveOrUpdateCate(@RequestBody PmsProductCategory pmsProductCategory){
        if (pmsProductCategory.getParentId()==0){
            pmsProductCategory.setLevel(0);
        }else {
            pmsProductCategory.setLevel(1);
        }
        ipmsProductCategoryService.saveOrUpdate(pmsProductCategory);
        return  ResultObject.success();
    }
    @GetMapping("{id}")
    public ResultObject getById(@PathVariable("id") Long id){
        PmsProductCategory byId = ipmsProductCategoryService.getById(id);
        return ResultObject.success(byId);
    }

    @GetMapping("select")
    public ResultObject getSelectList(){
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.eq("parent_id",0);
        pmsProductCategoryQueryWrapper.orderByDesc("sort");
        pmsProductCategoryQueryWrapper.select("id,name");
        List<PmsProductCategory> list = ipmsProductCategoryService.list(pmsProductCategoryQueryWrapper);
        return ResultObject.success(list);
    }

    @GetMapping("CategorySelectList")
    public ResultObject queryCategorySelectList(){
        QueryWrapper<PmsProductCategory> pmsProductCategoryQueryWrapper = new QueryWrapper<>();
        pmsProductCategoryQueryWrapper.orderByDesc("sort");
        pmsProductCategoryQueryWrapper.select("id,name,parent_id,level");
        List<PmsProductCategory> list = ipmsProductCategoryService.list(pmsProductCategoryQueryWrapper);
        List<Map<String, Object>> tree = getTree(list, 0l);
        return ResultObject.success(tree);
    }

    private List<Map<String, Object>> getTree(List<PmsProductCategory> productList, Long pid) {
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        productList.forEach(prod->{
            Map<String,Object> map=null;
            if (pid.equals(prod.getParentId())){
                map=new HashMap<>();
                map.put("value",prod.getId());
                map.put("label",prod.getName());
                if (prod.getLevel().equals(0)){
                    map.put("children",getTree(productList,prod.getId()));
                }
            }
            if (map!=null){
                list.add(map);
            }
        });
        return list;
    }

}
