package com.fh.adminandmenus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author diao
 * @since 2021-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminMenus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("adminId")
    private Long adminid;

    @TableField("menusId")
    private Long menusid;


}
