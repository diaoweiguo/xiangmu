package com.fh.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.productattribute.entity.PmsProductAttribute;
import lombok.Data;

@Data
public class PmsProductAttributeSearch extends Page<PmsProductAttribute> {

    private Long pid;
    private Integer  type;

}
