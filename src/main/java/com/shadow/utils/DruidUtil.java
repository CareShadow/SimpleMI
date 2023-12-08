package com.shadow.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.shadow.domain.MiBaseDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DruidUtil
 * @Description TODO
 * @Author Administrator
 * @Date 2023/12/7 0007 20:23
 * @Version 1.0
 **/
@Slf4j
public final class DruidUtil {
    private final static Map<String, DruidDataSource> dataSources = new HashMap<>();

    /**
     * @param dataSource
     * @return
     * @Description  获取一个连接池, 并生成一个连接
     */
    public static DruidPooledConnection getConnection(MiBaseDataSource dataSource) {
        DruidDataSource source = null;
        String datasourceId = dataSource.getId();
        if (dataSources.get(datasourceId) == null) {
            source = new DruidDataSource();
            dataSources.put(datasourceId, source);
        }else {
            source = dataSources.get(datasourceId);
        }
        String dataUrl = getUrlHeader(dataSource);
        log.debug("数据库的URL为{}", dataUrl);
        source.setUrl(dataUrl);
        source.setUsername(dataSource.getUser());
        source.setPassword(dataSource.getPassword());
        source.setConnectionErrorRetryAttempts(2);  // 设置重试次数为2
        source.setBreakAfterAcquireFailure(true); // 开启重试次数
        try {
            DruidPooledConnection connection = source.getConnection();
            return connection;
        } catch (SQLException e) {
            log.debug(e.getMessage());
        }
        return null;
    }

    private static String getUrlHeader(MiBaseDataSource dataSource) {
        String type = dataSource.getType();
        // jdbc:mysql://120.76.202.102:3306/snest-mi?useSSL=false&useUnicode=true&characterEncoding=utf-8
        String urlHeader = "";
        switch (type) {
            case "MySQL":
                urlHeader = "jdbc:mysql://";
                break;
            case "Oracle":
                urlHeader = "jdbc:oracle:thin:@//";
                break;
            case "ClickHouse":
                urlHeader = "jdbc:clickhouse://";
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(urlHeader).append(dataSource.getHost())
                .append(":").append(dataSource.getPort())
                .append("/").append(dataSource.getDatabaseName());
        String dataUrl = sb.toString();
        return dataUrl;
    }
}
