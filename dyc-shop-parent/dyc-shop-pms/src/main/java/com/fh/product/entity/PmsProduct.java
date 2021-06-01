package com.fh.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author diao
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 品牌id··············5··············
     */
    private Long brandId;

    /**
     * 品牌分类id·······1····················
     */
    private Long productCategoryId;

    /**
     * 品牌分类pid·······1····················
     */
    private Long productCategoryPid;

    /**
     * 运费模版id
     */
    private Long feightTemplateId;

    /**
     * 品牌属性分类id··········4··············
     */
    private Long productAttributeCategoryId;

    /**
     * 商品名称·······2·····
     */
    private String name;

    /**
     * 图片
     */
    private String pic;

    /**
     * 货号···········7············
     */
    private String productSn;

    /**
     * 删除状态：0->未删除；1->已删除
     */
    private Integer deleteStatus;

    /**
     * 上架状态：0->下架；1->上架
     */
    private Integer publishStatus;

    /**
     * 新品状态:0->不是新品；1->新品
     */
    private Integer newStatus;

    /**
     * 推荐状态；0->不推荐；1->推荐
     */
    private Integer recommandStatus;

    /**
     * 审核状态：0->未审核；1->审核通过
     */
    private Integer verifyStatus;

    /**
     * 排序················13···················
     */
    private Integer sort;

    /**
     * 销量
     */
    private Integer sale;

    /**
     * 价格,················8·······
     */
    private Double price;

    /**
     * 促销价格
     */
    private Double promotionPrice;

    /**
     * 赠送的成长值
     */
    private Integer giftGrowth;

    /**
     * 赠送的积分
     */
    private Integer giftPoint;

    /**
     * 限制使用的积分数
     */
    private Integer usePointLimit;

    /**
     * 副标题···········3·······
     */
    private String subTitle;

    /**
     * 商品描述·············6···········
     */
    private String description;

    /**
     * 市场价···············9·············
     */
    private BigDecimal originalPrice;

    /**
     * 库存,·······················10············
     */
    private Integer stock;

    /**
     * 库存预警值
     */
    private Integer lowStock;

    /**
     * 单位·······················11······················
     */
    private String unit;

    /**
     * 商品重量，默认为克················12···········
     */
    private BigDecimal weight;

    /**
     * 是否为预告商品：0->不是；1->是
     */
    private Integer previewStatus;

    /**
     * 以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
     */
    private String serviceIds;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 备注
     */
    private String note;

    /**
     * 画册图片，连产品图片限制为5张，以逗号分割
     */
    private String albumPics;

    /**
     * 详情标题
     */
    private String detailTitle;

    /**
     * 详情描述
     */
    private String detailDesc;

    /**
     * 产品详情网页内容
     */
    private String detailHtml;

    /**
     * 移动端网页详情
     */
    private String detailMobileHtml;

    /**
     * 促销开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss",timezone = "GMT+8")
    private Date promotionStartTime;

    /**
     * 促销结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss",timezone = "GMT+8")
    private Date promotionEndTime;

    /**
     * 活动限购数量
     */
    private Integer promotionPerLimit;

    /**
     * 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
     */
    private Integer promotionType;

    /**
     * 产品分类名称
     */
    private String productCategoryName;

    /**
     * 品牌名称
     */
    private String brandName;


}
