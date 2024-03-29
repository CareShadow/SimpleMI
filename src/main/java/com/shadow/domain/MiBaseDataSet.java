package com.shadow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * mi_base_data_set
 * @TableName mi_base_data_set
 */
@Data
public class MiBaseDataSet implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 数据集名称
     */
    private String name;

    /**
     * 数据集类型
     */
    private String type;

    /**
     * 数据源ID
     */
    private String sourceId;

    /**
     * 执行SQL
     */
    private String executeSql;

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