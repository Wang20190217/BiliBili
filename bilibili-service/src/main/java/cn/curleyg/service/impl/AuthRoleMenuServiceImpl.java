package cn.curleyg.service.impl;

import cn.curleyg.entity.AuthRoleMenu;
import cn.curleyg.mapper.AuthRoleMenuMapper;
import cn.curleyg.service.IAuthRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限控制--角色页面菜单关联表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Service
public class AuthRoleMenuServiceImpl extends ServiceImpl<AuthRoleMenuMapper, AuthRoleMenu> implements IAuthRoleMenuService {

}
