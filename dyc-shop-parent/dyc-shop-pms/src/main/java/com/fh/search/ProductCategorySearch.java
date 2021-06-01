package com.fh.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.productcategory.entity.PmsProductCategory;
import lombok.Data;

@Data
public class ProductCategorySearch extends Page<PmsProductCategory> {

    private Long pid;

}
