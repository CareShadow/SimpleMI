package com.shadow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * mi_base_data_source
 * @TableName mi_base_data_source
 */
@Data
public class MiBaseDataSource implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 连接类型
     */
    private String type;

    /**
     * 授权方式
     */
    private String authType;

    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}