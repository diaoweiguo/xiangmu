package com.fh.skustock.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.skustock.entity.PmsSkuStock;
import com.fh.skustock.mapper.PmsSkuStockMapper;
import com.fh.skustock.service.IPmsSkuStockService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-27
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements IPmsSkuStockService {

    @Override
    public void saveOrUpdateSkuStock(Long id, List<PmsSkuStock> skuStockList) {
        for (int i = 0; i <skuStockList.size() ; i++) {
            skuStockList.get(i).setCreateTime(new Date());
            skuStockList.get(i).setUpdateTime(new Date());
            skuStockList.get(i).setProductId(id);
            skuStockList.get(i).setSkuCode(IdWorker.getIdStr());
        }
        saveOrUpdateBatch(skuStockList);
    }
}
