package com.shadow.controller;

import com.shadow.entity.User;
import com.shadow.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/24 23:02
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 测试
     * @return
     */
    @GetMapping("/test")
    public String index() {
        return "Hello MI";
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String generate = JwtUtil.generate(user.getName());
        return generate;
    }

    @GetMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(20);
        // 注意哦，这里是直接返回的User类型，并没有用ResultVO进行包装
        return user;
    }

}
