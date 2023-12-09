package com.shadow.controller;

import cn.hutool.core.lang.ObjectId;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.shadow.domain.MiBaseDataSource;
import com.shadow.service.MiBaseDataSourceService;
import com.shadow.utils.DruidUtil;
import com.shadow.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseDataController
 * @Description TODO
 * @Author Administrator
 * @Date 2023/12/4 0004 19:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/base")
public class BaseDataController {

    @Resource
    private MiBaseDataSourceService dataSourceService;


    /**
     * 新增数据源
     *
     * @param dataSource
     * @return
     */
    @PostMapping("/insert")
    public ResultVO<String> insertDataSource(@RequestBody MiBaseDataSource dataSource) {
        dataSource.setId(ObjectId.next());
        boolean isSuccess = dataSourceService.save(dataSource);
        String msg = isSuccess ? "操作成功" : "操作失败";
        return new ResultVO<String>(msg);
    }

    /**
     * 修改数据源
     *
     * @param dataSource
     * @return
     */
    @PostMapping("/edit")
    public ResultVO<String> editDataSource(@RequestBody MiBaseDataSource dataSource) {
        boolean isSuccess = dataSourceService.updateById(dataSource);
        String msg = isSuccess ? "操作成功" : "操作失败";
        return new ResultVO<String>(msg);
    }

    /**
     * 链接数据源
     *
     * @param dataSourceId
     * @return
     */
    @PostMapping("/connect")
    public ResultVO<String> connectDataSource(String dataSourceId) {
        MiBaseDataSource datasource = dataSourceService.getById(dataSourceId);
        DruidPooledConnection connection = DruidUtil.getConnection(datasource);
        String msg = "";
        if (connection == null) {
            msg = "连接失败";
        } else {
            msg = "连接成功";
        }
        return new ResultVO<String>(msg);
    }

    /**
     * 执行SQL语句
     * @param dataSourceId
     * @param sql
     * @return
     */
    @PostMapping("/executeSql")
    public ResultVO<List<Map>> executeSql(String dataSourceId, String sql) {
        MiBaseDataSource datasource = dataSourceService.getById(dataSourceId);
        DruidPooledConnection connection = DruidUtil.getConnection(datasource);
        List<Map> resultSet = DruidUtil.ExecuteSQL(sql, connection);
        return new ResultVO<>(resultSet);
    }
}
