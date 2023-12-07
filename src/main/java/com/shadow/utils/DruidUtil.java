package com.shadow.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.shadow.domain.MiBaseDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * @ClassName DruidUtil
 * @Description TODO
 * @Author Administrator
 * @Date 2023/12/7 0007 20:23
 * @Version 1.0
 **/
@Slf4j
public final class DruidUtil {
    /**
     * @deprecated 获取一个连接池,并生成一个连接
     * @param dataSource
     * @return
     */
    public static DruidPooledConnection getConnection(MiBaseDataSource dataSource) {
        DruidDataSource source = new DruidDataSource();
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
        source.setUrl(dataUrl);
        source.setUsername(dataSource.getUser());
        source.setPassword(dataSource.getPassword());
        try {
            DruidPooledConnection connection = source.getConnection();
            return connection;
        } catch (SQLException e) {
            log.debug(e.getMessage());
            return null;
        }
    }
}
