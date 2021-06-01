package com.fh.memberprice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author diao
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsMemberPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 会员等级id
     */
    private Long memberLevelId;

    /**
     * 会员价格
     */
    private BigDecimal memberPrice;

    /**
     * 会员等级名称
     */
    private String memberLevelName;


}
