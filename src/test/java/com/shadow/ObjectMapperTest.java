package com.shadow;

import cn.hutool.core.lang.ObjectId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shadow.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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
        String id = ObjectId.next();
        System.out.println(id);
    }
}
