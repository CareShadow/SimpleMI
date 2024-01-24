package com.shadow.controller;

import cn.hutool.core.lang.ObjectId;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.context.UserContext;
import com.shadow.domain.MiBaseDataSet;
import com.shadow.domain.MiBaseDataSource;
import com.shadow.enums.ResultCode;
import com.shadow.service.MiBaseDataSourceService;
import com.shadow.utils.DruidUtil;
import com.shadow.vo.ResultBuilder;
import com.shadow.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

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
        dataSource.setCreateUser(UserContext.getCurrentUserName());
        dataSource.setCreateDate(new Date());
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
     * @param dataset
     * @return
     */
    @PostMapping("/executeSql")
    public ResultVO<List<Map>> executeSql(@RequestBody MiBaseDataSet dataset) throws SQLException {
        String dataSourceId = dataset.getSourceId();
        String sql = dataset.getExecuteSql();
        MiBaseDataSource datasource = dataSourceService.getById(dataSourceId);
        DruidPooledConnection connection = DruidUtil.getConnection(datasource);
        List<Map> resultSet = DruidUtil.ExecuteSQL(sql, connection);
        return ResultBuilder.ok(resultSet);
    }

    /**
     * 获取数据源列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVO<Map> dataSourceList() {
        Map<String, Object> result = new HashMap<>();
        List<MiBaseDataSource> list = dataSourceService
                .list(new QueryWrapper<MiBaseDataSource>()
                        .select("id", "name", "host", "port", "create_user", "create_date", "type", "database_name"));
        int count = dataSourceService.count();
        result.put("count", count);
        result.put("list", list);
        return ResultBuilder.ok(result);
    }

    /**
     * 获取数据源信息
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/get/{datasourceId}")
    public ResultVO<MiBaseDataSource> getDataSource(@PathVariable String datasourceId) {
        MiBaseDataSource datasource = dataSourceService.getById(datasourceId);
        return ResultBuilder.ok(datasource);
    }

    /**
     * 删除数据源
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/delete/{datasourceId}")
    public ResultVO deleteDataSource(@PathVariable String datasourceId) {
        // todo 数据集引用的数据集不能删除
        boolean isDelete = dataSourceService.removeById(datasourceId);
        String msg = isDelete ? "删除成功" : "删除失败";
        return ResultBuilder.create(ResultCode.SUCCESS, msg);
    }

    /**
     * 数据集绑定数据源列表
     *
     * @return
     */
    @GetMapping("/enums/list")
    public ResultVO getDateSource() {
        List<MiBaseDataSource> sources = dataSourceService.list();
        List<Map<String, Object>> result = new ArrayList<>();
        for (MiBaseDataSource source : sources) {
            Map<String, Object> item = new HashMap<>();
            String sourceCode = source.getId();
            String name = source.getName();
            item.put("code", sourceCode);
            item.put("name", name);
            result.add(item);
        }
        return ResultBuilder.ok(result);
    }
}
