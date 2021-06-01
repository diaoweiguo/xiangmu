package com.fh.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.product.entity.PmsProduct;
import com.fh.result.ResultObject;
import com.fh.search.PmsProductBo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-23
 */
public interface IPmsProductService extends IService<PmsProduct> {

    void saveOrUpdateProduct(PmsProduct pmsProduct);
}
