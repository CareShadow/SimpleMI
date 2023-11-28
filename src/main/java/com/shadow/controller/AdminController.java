package com.shadow.controller;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.domain.RbacUser;
import com.shadow.service.RbacUserService;
import com.shadow.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private RbacUserService rbacUserService;

    /**
     * 新建用户
     *
     * @return
     */
    @PostMapping("/insert")
    public String insertUser(@RequestBody RbacUser user) {
        user.setId(ObjectId.next());
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(user.getPassword());

        user.setPassword(digestHex);
        rbacUserService.save(user);
        return "操作成功";
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody RbacUser user) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(user.getPassword());
        RbacUser rbacUser = rbacUserService.getOne(new QueryWrapper<RbacUser>()
                .lambda().eq(RbacUser::getPassword, digestHex)
                .eq(RbacUser::getLogin, user.getLogin()));
        String token = JwtUtil.generate(user.getLogin());
        return rbacUser == null ? "登录失败" : token;
    }


}
