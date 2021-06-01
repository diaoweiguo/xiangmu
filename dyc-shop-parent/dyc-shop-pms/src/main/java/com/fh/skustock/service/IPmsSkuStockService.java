package com.fh.skustock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.skustock.entity.PmsSkuStock;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-27
 */
public interface IPmsSkuStockService extends IService<PmsSkuStock> {

    void saveOrUpdateSkuStock(Long id, List<PmsSkuStock> skuStockList);
}
