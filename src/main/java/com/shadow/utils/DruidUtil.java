package com.shadow.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.shadow.domain.MiBaseDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ClassName DruidUtil
 * @Description TODO
 * @Author Administrator
 * @Date 2023/12/7 0007 20:23
 * @Version 1.0
 **/
@Slf4j
public final class DruidUtil {
    private final static Map<String, DruidDataSource> DATASOURCES = new HashMap<>();

    /**
     * @param dataSource
     * @return
     * @Description 获取一个连接池, 并生成一个连接
     */
    public static DruidPooledConnection getConnection(MiBaseDataSource dataSource) {
        DruidDataSource source;
        DruidPooledConnection connection = null;
        String datasourceId = dataSource.getId();
        if (DATASOURCES.get(datasourceId) == null) {
            source = new DruidDataSource();
            String dataUrl = getUrlHeader(dataSource);
            log.debug("数据库的URL为{}", dataUrl);
            source.setUrl(dataUrl);
            source.setUsername(dataSource.getUser());
            source.setPassword(dataSource.getPassword());
            source.setBreakAfterAcquireFailure(true); // 开启重试次数
            source.setConnectionErrorRetryAttempts(2);  // 设置重试次数为2
            source.setTestWhileIdle(true); // 开启空闲连接检测
            source.setTimeBetweenEvictionRunsMillis(60000); // 设置检验时间间隔
            source.setMinEvictableIdleTimeMillis(300000); // 设置连接的存活时间
            DATASOURCES.put(datasourceId, source);
        } else {
            source = DATASOURCES.get(datasourceId);
        }
        try {
            connection = source.getConnection();
        } catch (SQLException e) {
            log.debug(e.getMessage());
        }
        return connection;
    }

    /**
     * 拼接不同数据源的URL
     *
     * @param dataSource
     * @return
     */
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
            default:
                throw new RuntimeException("暂不支持该数据库");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(urlHeader).append(dataSource.getHost()).append(":").append(dataSource.getPort()).append("/").append(dataSource.getDatabaseName());
        String dataUrl = sb.toString();
        return dataUrl;
    }

    public static List<Map> ExecuteSQL(String sql, DruidPooledConnection connection) throws SQLException {
        List<Map> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Map<String, Object> map = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnLabel(i);
                    Object value = resultSet.getObject(i);
                    // 对原始的timestamp类型进行处理
                    if (value instanceof Timestamp) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String format = ((Timestamp) value).toLocalDateTime().format(formatter);
                        value = format;
                    }
                    map.put(columnName, value);
                }
                result.add(map);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }
}
