package com.shadow.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName rbac_user
 */
@Data
public class RbacUser implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 登录名
     */
    private String login;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createData;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private String updateUser;

    private static final long serialVersionUID = 1L;
}