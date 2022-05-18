package cn.curleyg.service.impl;

import cn.curleyg.entity.AuthRoleMenu;
import cn.curleyg.entity.UserAuthorities;
import cn.curleyg.entity.UserRole;
import cn.curleyg.mapper.AuthRoleElementOperationMapper;
import cn.curleyg.mapper.AuthRoleMenuMapper;
import cn.curleyg.mapper.UserRoleMapper;
import cn.curleyg.service.IUserAuthService;
import cn.curleyg.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限查询实现类
 * </p>
 *
 * @author: Wang
 * @since: 2022/5/18 12:26 <br>
 */
@Service
public class UserAuthServiceImpl implements IUserAuthService {

    @Autowired
    AuthRoleMenuMapper authRoleMenu;

    @Autowired
    AuthRoleElementOperationMapper elementOperationMapper;

    @Autowired
    IUserRoleService userRoleService;

    @Override
    public UserAuthorities getUserAuthorities(Long userId) {
        //先获取角色
        List<UserRole> userRoles = userRoleService.getUserRoleListByUserId(userId);
        return null;
    }
}
