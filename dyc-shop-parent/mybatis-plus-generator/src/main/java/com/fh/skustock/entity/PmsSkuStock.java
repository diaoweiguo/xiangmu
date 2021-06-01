package com.fh.skustock.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author diao
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsSkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * sku的编码值
     */
    private String skuCode;

    /**
     * 预警库存
     */
    private Integer lowStock;

    /**
     * 图片
     */
    private String pic;

    /**
     * 销量
     */
    private Integer sale;

    /**
     * 促销价格
     */
    private BigDecimal promotionPrice;

    /**
     * 商品销售属性，json格式
     */
    private String spData;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
