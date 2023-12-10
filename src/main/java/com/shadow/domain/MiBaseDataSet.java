package com.shadow.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
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
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 执行SQL
     */
    private byte[] executeSql;

    private static final long serialVersionUID = 1L;
}