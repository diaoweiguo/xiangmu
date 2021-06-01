package com.fh.product.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.memberprice.entity.PmsMemberPrice;
import com.fh.memberprice.service.IPmsMemberPriceService;
import com.fh.po.RolePo;
import com.fh.product.entity.PmsProduct;
import com.fh.product.service.IPmsProductService;
import com.fh.productattribute.entity.PmsProductAttribute;
import com.fh.productattributevalue.entity.PmsProductAttributeValue;
import com.fh.productattributevalue.service.IPmsProductAttributeValueService;
import com.fh.productfullreduction.entity.PmsProductFullReduction;
import com.fh.productfullreduction.service.IPmsProductFullReductionService;
import com.fh.productladder.entity.PmsProductLadder;
import com.fh.productladder.service.IPmsProductLadderService;
import com.fh.result.ResultObject;
import com.fh.search.PmsProductBo;
import com.fh.skustock.entity.PmsSkuStock;
import com.fh.skustock.service.IPmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/pms/product")
public class PmsProductController {

    @Autowired
    private IPmsProductService  ipmsProductService;

    @Autowired
    private  IPmsProductLadderService ipmsProductLadderService;

    @Autowired
    private IPmsProductFullReductionService   ipmsProductFullReductionService;

    @Autowired
    private  IPmsMemberPriceService   ipmsMemberPriceService;

    @Autowired
    private IPmsProductAttributeValueService   ipmsProductAttributeValueService;

    @Autowired
    private IPmsSkuStockService    ipmsSkuStockService;


    @PostMapping
    @Transactional
    public ResultObject saveOrUpdateProduct(@RequestBody PmsProductBo pmsProductBo){
        ipmsProductService.saveOrUpdateProduct(pmsProductBo.getPmsProduct());
       /* if (pmsProductBo.getPmsProduct().getPromotionType().equals(3)){
            ipmsProductLadderService.saveOrUpdateProductLadder(pmsProductBo.getPmsProduct().getId(),pmsProductBo.getProductLadderList());
        }
        if (pmsProductBo.getPmsProduct().getPromotionType().equals(4)){
            ipmsProductFullReductionService.saveOrUpdateProductFullReduction(pmsProductBo.getPmsProduct().getId(),pmsProductBo.getProductFullReductionList());
        }*/
        ipmsProductLadderService.saveOrUpdateProductLadder(pmsProductBo.getPmsProduct().getId(),pmsProductBo.getProductLadderList());
        ipmsProductFullReductionService.saveOrUpdateProductFullReduction(pmsProductBo.getPmsProduct().getId(),pmsProductBo.getProductFullReductionList());
        ipmsMemberPriceService.saveOrUpdateMemberPrice(pmsProductBo.getPmsProduct().getId(),pmsProductBo.getMemBerPriceList());
        ipmsProductAttributeValueService.saveOrUpdateProductAttributeValue(pmsProductBo.getPmsProduct().getId(),pmsProductBo.getProductAttributeValueList());
        ipmsSkuStockService.saveOrUpdateSkuStock(pmsProductBo.getPmsProduct().getId(),pmsProductBo.getSkuStockList());
        return ResultObject.success();
    }


    @GetMapping("{productId}")
    public PmsProductBo getById(@PathVariable("productId") Long productId){
        /*商品*/
        PmsProductBo pmsProductBo = new PmsProductBo();
        if (productId == null){
            return pmsProductBo;
        }
        PmsProduct pmsProduct = ipmsProductService.getById(productId);
        pmsProductBo.setPmsProduct(pmsProduct);
        Map<String,Object> map=new HashMap<>();
        map.put("product_id",productId);
        /*会员价格*/
        List<PmsMemberPrice> MemBerPriceList = ipmsMemberPriceService.listByMap(map);
        pmsProductBo.setMemBerPriceList(MemBerPriceList);
        /*阶梯价格*/
        List<PmsProductLadder> pmsProductLadders = ipmsProductLadderService.listByMap(map);
        pmsProductBo.setProductLadderList(pmsProductLadders);
        /*满减价格*/
        List<PmsProductFullReduction> pmsProductFullReductions = ipmsProductFullReductionService.listByMap(map);
        pmsProductBo.setProductFullReductionList(pmsProductFullReductions);
        /*sku数据*/
        List<PmsSkuStock> pmsSkuStocks = ipmsSkuStockService.listByMap(map);
        pmsProductBo.setSkuStockList(pmsSkuStocks);
        /*商品属性值数据*/
        List<PmsProductAttributeValue> pmsProductAttributeValues = ipmsProductAttributeValueService.listByMap(map);
        pmsProductBo.setProductAttributeValueList(pmsProductAttributeValues);
        return pmsProductBo;
    }

    @GetMapping
    public IPage<PmsProduct> queryProductList(Page<PmsProduct> page){
        QueryWrapper<PmsProduct> queryWrapper=new QueryWrapper<PmsProduct>();
        queryWrapper.orderByDesc("id");
        return ipmsProductService.page(page,queryWrapper);
    }


    @DeleteMapping
    public ResultObject deleteRole(@RequestBody String ids){
        List<Long> longs = JSONArray.parseArray(ids, Long.class);
        ipmsProductService.removeByIds(longs);
        return ResultObject.success();
    }




    @DeleteMapping("{id}")
    public ResultObject DeleteById(@PathVariable("id") Long id){
        ipmsProductService.removeById(id);
        return ResultObject.success();
    }








}
