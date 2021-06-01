package com.fh.productattributevalue.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class PmsProductAttributeValue implements Serializable {

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
     * 属性ID
     */
    private Long arrtibuteId;

    /**
     * 属性值
     */
    private String value;


}
