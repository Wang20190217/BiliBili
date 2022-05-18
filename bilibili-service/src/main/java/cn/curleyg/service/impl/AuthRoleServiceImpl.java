package cn.curleyg.service.impl;

import cn.curleyg.entity.AuthRole;
import cn.curleyg.mapper.AuthRoleMapper;
import cn.curleyg.service.IAuthRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限控制--角色表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements IAuthRoleService {

}
