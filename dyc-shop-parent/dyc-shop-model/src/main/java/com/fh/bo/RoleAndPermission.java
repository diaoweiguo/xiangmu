package com.fh.bo;

import lombok.Data;

import java.util.List;
@Data
public class RoleAndPermission {

    private Long roleId;

    private List<Long> permList;

}
