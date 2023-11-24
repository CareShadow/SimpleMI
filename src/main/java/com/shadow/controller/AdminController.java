package com.shadow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author 18451
 * @Date 2023/11/24 23:02
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/test")
    public String index() {
        return "Hello MI";
    }
}
