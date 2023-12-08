package com.shadow;

import cn.hutool.core.lang.ObjectId;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadow.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.UUID;

/**
 * @ClassName ObjectMapperTest
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/26 22:55
 * @Version 1.0
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
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
        String id = ObjectId.next();
        System.out.println(id);
    }

    /**
     * 测试MySQL数据源的连接
     */
    @Test
    public void test02() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl("jdbc:mysql://120.76.202.102:3306/snest-mi?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        source.setUsername("root");
        source.setPassword("Liang2001...");
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
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

}
