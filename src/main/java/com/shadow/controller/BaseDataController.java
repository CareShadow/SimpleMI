package com.shadow.controller;

import com.shadow.domain.MiBaseDataSource;
import com.shadow.service.MiBaseDataSourceService;
import com.shadow.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
     * @param dataSource
     * @return
     */
    @PostMapping("/insert")
    public ResultVO<String> insertDataSource(@RequestBody MiBaseDataSource dataSource) {
        boolean isSuccess = dataSourceService.save(dataSource);
        String msg = isSuccess ? "操作成功" : "操作失败";
        return new ResultVO<String>(msg);
    }

    /**
     * 修改数据源
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
     * @param dataSourceId
     * @return
     */
    @PostMapping("/connect")
    public ResultVO<String> connectDataSource(String dataSourceId) {
        MiBaseDataSource datasource = dataSourceService.getById(dataSourceId);

        return new ResultVO<String>(null);
    }

}
