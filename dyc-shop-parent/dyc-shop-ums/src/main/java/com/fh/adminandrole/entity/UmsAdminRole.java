package com.fh.adminandrole.entity;

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
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    @TableField("roleId")
    private Long roleid;

    /**
     * 用户di
     */
    @TableField("adminId")
    private Long adminid;


}
