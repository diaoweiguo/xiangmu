package com.fh.productattributevalue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.productattributevalue.entity.PmsProductAttributeValue;
import com.fh.productattributevalue.mapper.PmsProductAttributeValueMapper;
import com.fh.productattributevalue.service.IPmsProductAttributeValueService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class PmsProductAttributeValueServiceImpl extends ServiceImpl<PmsProductAttributeValueMapper, PmsProductAttributeValue> implements IPmsProductAttributeValueService {

    @Override
    public void saveOrUpdateProductAttributeValue(Long id, List<PmsProductAttributeValue> productAttributeValueList){
        for (int i = 0; i <productAttributeValueList.size() ; i++) {
            productAttributeValueList.get(i).setProductId(id);
        }
        saveOrUpdateBatch(productAttributeValueList);
    }
}
