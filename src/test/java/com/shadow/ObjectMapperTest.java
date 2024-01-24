package com.shadow;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadow.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName ObjectMapperTest
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/26 22:55
 * @Version 1.0
 **/
@Slf4j
public class ObjectMapperTest {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String testStr = "careshadow";
        try {
            String json = objectMapper.writeValueAsString(new ResultVO<>(testStr));
            log.debug(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(UUID.randomUUID());
    }

    @Test
    public void test01() {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex("Liang2001...");
        System.out.println(digestHex);
    }

    /**
     * 测试MySQL数据源的连接
     */
    @Test
    public void test02() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl("jdbc:mysql://120.76.202.102:3306/snest-mi");
        source.setUsername("root");
        source.setPassword("Liang2001...");
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setConnectionErrorRetryAttempts(2);
        source.setBreakAfterAcquireFailure(true); // 开启重试次数
        try {
            DruidPooledConnection connection = source.getConnection();
            if (connection != null) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        } catch (SQLException e) {
            log.debug(e.getMessage());
        }
    }

    /**
     * 支持Service_Name连接
     * 测试Oracle数据源的连接
     */
    @Test
    public void test03() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl("jdbc:oracle:thin:@//120.76.202.102:49161/XE");
        source.setUsername("system");
        source.setPassword("oracle");
        // source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        try {
            DruidPooledConnection connection = source.getConnection();
            if (connection != null) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
            }
        } catch (SQLException e) {
            log.debug(e.getMessage());
        }
    }

    /**
     * 测试一下SQL的执行
     */
    @Test
    public void test04() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl("jdbc:mysql://120.76.202.102:3306/snest-mi");
        source.setUsername("root");
        source.setPassword("Liang2001...");
        DruidPooledConnection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = source.getConnection();
            String sql = "select * from table";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Map> result = new ArrayList<>();
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnLabel(i);
                    Object value = resultSet.getObject(i);
                    map.put(columnName, value);
                }
                result.add(map);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String resultJson = objectMapper.writeValueAsString(result);
            System.out.println(resultJson);
        } catch (SQLException | JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
