package com.fh.productfullreduction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.productfullreduction.entity.PmsProductFullReduction;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-24
 */
public interface IPmsProductFullReductionService extends IService<PmsProductFullReduction> {

    void saveOrUpdateProductFullReduction(Long id, List<PmsProductFullReduction> productFullReductionList);
}
