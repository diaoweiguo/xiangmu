package com.fh.memberprice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.memberprice.entity.PmsMemberPrice;
import com.fh.memberprice.mapper.PmsMemberPriceMapper;
import com.fh.memberprice.service.IPmsMemberPriceService;
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
public class PmsMemberPriceServiceImpl extends ServiceImpl<PmsMemberPriceMapper, PmsMemberPrice> implements IPmsMemberPriceService {

    @Override
    public void saveOrUpdateMemberPrice(Long id, List<PmsMemberPrice> memBerPriceList) {
        if (CollectionUtils.isEmpty(memBerPriceList)){
            return ;
        }
        QueryWrapper<PmsMemberPrice> pmsProductLadderQueryWrapper = new QueryWrapper<>();
        pmsProductLadderQueryWrapper.eq("product_id",id);
        remove(pmsProductLadderQueryWrapper);
        for (int i = 0; i <memBerPriceList.size() ; i++) {
            memBerPriceList.get(i).setProductId(id);
        }
        saveBatch(memBerPriceList);
    }

}
