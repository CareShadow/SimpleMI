package com.shadow.controller;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.context.UserContext;
import com.shadow.domain.RbacUser;
import com.shadow.enums.ResultCode;
import com.shadow.service.RbacUserService;
import com.shadow.utils.JwtUtil;
import com.shadow.vo.ResultBuilder;
import com.shadow.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

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
    public ResultVO<Object> insertUser(@RequestBody RbacUser user) {
        RbacUser entity = rbacUserService.getOne(new QueryWrapper<RbacUser>().lambda()
                .eq(RbacUser::getLogin, user.getLogin()));
        if (entity != null) {
            return ResultBuilder.create(ResultCode.SUCCESS, "该用户名已存在，请注册不同的用户名");
        }
        user.setId(ObjectId.next());
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(user.getPassword());
        user.setPassword(digestHex);
        boolean isSave = rbacUserService.save(user);
        return ResultBuilder.create(ResultCode.SUCCESS, "新增成功");
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody RbacUser user) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(user.getPassword());
        RbacUser rbacUser = rbacUserService.getOne(new QueryWrapper<RbacUser>().lambda().eq(RbacUser::getPassword, digestHex).eq(RbacUser::getLogin, user.getLogin()));
        String token = JwtUtil.generate(user.getLogin());
        return rbacUser == null ? ResultBuilder.create(ResultCode.UNAUTHORIZED, "用户名或密码错误") : ResultBuilder.ok(token);
    }

    /**
     * 获取当前账号信息
     *
     * @return
     */
    @GetMapping("/userInfo")
    public ResultVO<String> userInfo() {
        String currentUserName = UserContext.getCurrentUserName();
        RbacUser user = rbacUserService.getOne(
                new QueryWrapper<RbacUser>()
                        .lambda()
                        .eq(RbacUser::getLogin, currentUserName));
        String name = user.getName();
        return ResultBuilder.ok(name);
    }
}
