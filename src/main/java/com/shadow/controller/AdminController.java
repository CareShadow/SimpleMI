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
    public ResultVO<String> login(@RequestBody RbacUser user) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(user.getPassword());
        RbacUser rbacUser = rbacUserService.getOne(new QueryWrapper<RbacUser>()
                .lambda().eq(RbacUser::getPassword, digestHex)
                .eq(RbacUser::getLogin, user.getLogin()));
        String token = JwtUtil.generate(user.getLogin());
        return rbacUser == null ? new ResultVO<String>(ResultCode.UNAUTHORIZED, "用户名或密码错误")
                : new ResultVO<String>(token);
    }

    @GetMapping("/userInfo")
    public ResultVO<String> userInfo() {
        String currentUserName = UserContext.getCurrentUserName();
        return new ResultVO<String>(currentUserName);
    }

}
