package com.fh.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.permission.entity.UmsPermission;
import lombok.Data;

@Data
/*@Component*/
public class PermissionSearch extends Page<UmsPermission> {

    private Long pid;

}
