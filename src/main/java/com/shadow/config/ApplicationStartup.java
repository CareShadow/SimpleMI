package com.shadow.config;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.domain.RbacUser;
import com.shadow.service.RbacUserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.annotation.Resource;

/**
 * @ClassName ApplicationStartup
 * @Description TODO
 * @Author Administrator
 * @Date 2024/3/3 0003 20:46
 * @Version 1.0
 **/
public class ApplicationStartup implements ApplicationRunner {
    @Resource
    private RbacUserService rbacUserService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化管理员用户
        RbacUser user = rbacUserService.getOne(new QueryWrapper<RbacUser>().lambda()
                .eq(RbacUser::getLogin, "admin"));
        if (user != null) return;
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex("admin@123");
        RbacUser admin = new RbacUser();
        admin.setId(ObjectId.next());
        admin.setLogin("admin");
        admin.setName("超级管理员");
        admin.setPassword(digestHex);
        rbacUserService.save(admin);
    }
}
