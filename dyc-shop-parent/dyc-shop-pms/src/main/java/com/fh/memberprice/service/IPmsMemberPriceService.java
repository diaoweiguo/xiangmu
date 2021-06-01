package com.fh.memberprice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.memberprice.entity.PmsMemberPrice;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-24
 */
public interface IPmsMemberPriceService extends IService<PmsMemberPrice> {

    void saveOrUpdateMemberPrice(Long id, List<PmsMemberPrice> memBerPriceList);
}
