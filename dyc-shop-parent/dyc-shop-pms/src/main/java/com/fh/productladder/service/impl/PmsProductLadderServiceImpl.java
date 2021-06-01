package com.fh.productladder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.productladder.entity.PmsProductLadder;
import com.fh.productladder.mapper.PmsProductLadderMapper;
import com.fh.productladder.service.IPmsProductLadderService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author diao
 * @since 2021-05-24
 */
@Service
public class PmsProductLadderServiceImpl extends ServiceImpl<PmsProductLadderMapper, PmsProductLadder> implements IPmsProductLadderService {
    @Override
    public void saveOrUpdateProductLadder(Long id, List<PmsProductLadder> productLadderList) {
        if (CollectionUtils.isEmpty(productLadderList)){
            return;
        }
        QueryWrapper<PmsProductLadder> pmsProductLadderQueryWrapper = new QueryWrapper<>();
        pmsProductLadderQueryWrapper.eq("product_id",id);
        remove(pmsProductLadderQueryWrapper);
        for (int i = 0; i <productLadderList.size() ; i++) {
            productLadderList.get(i).setProductId(id);
        }
        saveBatch(productLadderList);
    }

}
