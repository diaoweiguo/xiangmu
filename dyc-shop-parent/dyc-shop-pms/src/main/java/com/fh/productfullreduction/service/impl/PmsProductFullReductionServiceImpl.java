package com.fh.productfullreduction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.productfullreduction.entity.PmsProductFullReduction;
import com.fh.productfullreduction.mapper.PmsProductFullReductionMapper;
import com.fh.productfullreduction.service.IPmsProductFullReductionService;
import com.fh.productladder.entity.PmsProductLadder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-24
 */
@Service
public class PmsProductFullReductionServiceImpl extends ServiceImpl<PmsProductFullReductionMapper, PmsProductFullReduction> implements IPmsProductFullReductionService {
    @Override
    public void saveOrUpdateProductFullReduction(Long id, List<PmsProductFullReduction> productFullReductionList) {
        if (CollectionUtils.isEmpty(productFullReductionList)){
            return ;
        }
        QueryWrapper<PmsProductFullReduction> pmsProductLadderQueryWrapper = new QueryWrapper<>();
        pmsProductLadderQueryWrapper.eq("product_id",id);
        remove(pmsProductLadderQueryWrapper);
        for (int i = 0; i <productFullReductionList.size() ; i++) {
            productFullReductionList.get(i).setProductId(id);
        }
        saveBatch(productFullReductionList);
    }
}
