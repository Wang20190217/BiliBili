package cn.curleyg.service.impl;

import cn.curleyg.entity.AuthRoleMenu;
import cn.curleyg.mapper.AuthRoleMenuMapper;
import cn.curleyg.service.IAuthRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    @Autowired
    AuthRoleMenuMapper authRoleMenuMapper;
    @Override
    public List<AuthRoleMenu> getRoleMenuByRoleIds(Set<Long> roleIdSet) {
        List<AuthRoleMenu> authRoleMenus=authRoleMenuMapper.getRoleMenuByRoleIds(roleIdSet);
        return authRoleMenus;
    }
}
