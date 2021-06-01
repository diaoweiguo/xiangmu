package com.fh.productladder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fh.productladder.entity.PmsProductLadder;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author diao
 * @since 2021-05-24
 */
public interface IPmsProductLadderService extends IService<PmsProductLadder> {

    void saveOrUpdateProductLadder(Long id, List<PmsProductLadder> productLadderList);
}
