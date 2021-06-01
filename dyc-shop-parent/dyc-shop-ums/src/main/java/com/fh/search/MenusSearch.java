package com.fh.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.menus.entity.UmsMenus;
import lombok.Data;

@Data
public class MenusSearch extends Page<UmsMenus> {

    private Long pid;

}
