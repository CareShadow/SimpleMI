package com.shadow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.domain.RbacUser;
import com.shadow.service.RbacUserService;
import com.shadow.mapper.RbacUserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【rbac_user】的数据库操作Service实现
* @createDate 2023-11-28 17:55:56
*/
@Service
public class RbacUserServiceImpl extends ServiceImpl<RbacUserMapper, RbacUser>
    implements RbacUserService{

}




