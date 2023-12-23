package com.shadow.controller;

import cn.hutool.core.lang.ObjectId;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.domain.MiBaseDataSource;
import com.shadow.enums.ResultCode;
import com.shadow.service.MiBaseDataSourceService;
import com.shadow.utils.DruidUtil;
import com.shadow.vo.ResultBuilder;
import com.shadow.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
        return ResultBuilder.create(ResultCode.SUCCESS, msg);
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
        return ResultBuilder.create(ResultCode.SUCCESS, msg);
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
        String msg = connection == null ? "连接失败" : "连接成功";
        return ResultBuilder.create(ResultCode.SUCCESS, msg);
    }

    /**
     * 执行SQL语句
     *
     * @param dataSourceId
     * @param sql
     * @return
     */
    @PostMapping("/executeSql")
    public ResultVO<List<Map>> executeSql(String dataSourceId, String sql) {
        MiBaseDataSource datasource = dataSourceService.getById(dataSourceId);
        DruidPooledConnection connection = DruidUtil.getConnection(datasource);
        List<Map> resultSet = DruidUtil.ExecuteSQL(sql, connection);
        return ResultBuilder.ok(resultSet);
    }

    /**
     * 获取数据源列表
     * @return
     */
    @GetMapping("/list")
    public ResultVO<Map> dataSourceList() {
        Map<String, Object> result = new HashMap<>();
        List<MiBaseDataSource> list = dataSourceService
                .list(new QueryWrapper<MiBaseDataSource>()
                        .select("id", "name", "host", "port", "create_user", "create_date", "type"));
        int count = dataSourceService.count();
        result.put("count", count);
        result.put("list", list);
        return ResultBuilder.ok(result);
    }
}
