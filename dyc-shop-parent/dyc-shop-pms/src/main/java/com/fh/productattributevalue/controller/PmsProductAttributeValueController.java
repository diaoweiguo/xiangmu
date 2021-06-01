package com.fh.productattributevalue.controller;


import com.alibaba.fastjson.JSON;
import com.fh.productattributevalue.entity.PmsProductAttributeValue;
import com.fh.productattributevalue.service.IPmsProductAttributeValueService;
import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author diao
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/pms/productattributevalue")
public class PmsProductAttributeValueController {

    @Autowired
    private IPmsProductAttributeValueService  ipmsProductAttributeValueService;


    @PostMapping
    public ResultObject  saveAttriButeValue(@RequestBody PmsProductAttributeValue  productAttributeValue){
        System.out.println(JSON.toJSONString(productAttributeValue));
      return   ResultObject.success();
    }






}
