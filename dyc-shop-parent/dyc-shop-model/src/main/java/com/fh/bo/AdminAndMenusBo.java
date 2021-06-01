package com.fh.bo;

import lombok.Data;

import java.util.List;
@Data
public class AdminAndMenusBo {

    private Long userId;

    private List<Long> menusIdList;

}
