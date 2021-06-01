package com.fh.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.brand.entity.PmsBrand;
import com.fh.brand.service.IPmsBrandService;
import com.fh.product.entity.PmsProduct;
import com.fh.product.mapper.PmsProductMapper;
import com.fh.product.service.IPmsProductService;
import com.fh.productcategory.entity.PmsProductCategory;
import com.fh.productcategory.service.IPmsProductCategoryService;
import com.fh.result.ResultObject;
import com.fh.search.PmsProductBo;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-23
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

     @Autowired
     private IPmsBrandService    ipmsBrandService;

     @Autowired
     private IPmsProductCategoryService     ipmsProductCategoryService;


    @Override
    public void saveOrUpdateProduct(PmsProduct pmsProduct) {
        //查询品牌名称
        PmsBrand Brand = ipmsBrandService.getById(pmsProduct.getBrandId());
        pmsProduct.setBrandName(Brand.getName());
        //查询产品分类名称
        PmsProductCategory ProductCategory = ipmsProductCategoryService.getById(pmsProduct.getProductCategoryId());
        pmsProduct.setProductCategoryName(ProductCategory.getName());
        saveOrUpdate(pmsProduct);
    }
}
