package com.shadow.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DataSet
 * @Description TODO
 * @Author CareShadow
 * @Date 2024/1/22 21:26
 * @Version 1.0
 **/
@Data
public class DataSet implements Serializable {
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
    private String sourceName;

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
