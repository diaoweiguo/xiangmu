package com.fh.search;

import com.fh.memberprice.entity.PmsMemberPrice;
import com.fh.product.entity.PmsProduct;
import com.fh.productattributevalue.entity.PmsProductAttributeValue;
import com.fh.productfullreduction.entity.PmsProductFullReduction;
import com.fh.productladder.entity.PmsProductLadder;
import com.fh.skustock.entity.PmsSkuStock;
import lombok.Data;

import java.util.List;
@Data
public class PmsProductBo {

    private PmsProduct pmsProduct;

    private List<PmsProductLadder> productLadderList;

    private List<PmsProductFullReduction> productFullReductionList;

    private List<PmsMemberPrice> memBerPriceList;

    private List<PmsProductAttributeValue>  productAttributeValueList;

    private List<PmsSkuStock>   skuStockList;



}
