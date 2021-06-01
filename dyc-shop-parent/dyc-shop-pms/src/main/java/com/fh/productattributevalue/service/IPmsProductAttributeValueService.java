package com.fh.productattributevalue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.productattributevalue.entity.PmsProductAttributeValue;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-27
 */
public interface IPmsProductAttributeValueService extends IService<PmsProductAttributeValue> {

    void saveOrUpdateProductAttributeValue(Long id, List<PmsProductAttributeValue> productAttributeValueList);
}
