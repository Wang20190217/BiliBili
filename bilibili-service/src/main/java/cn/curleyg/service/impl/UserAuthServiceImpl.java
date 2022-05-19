package cn.curleyg.service.impl;

import cn.curleyg.entity.AuthRoleElementOperation;
import cn.curleyg.entity.AuthRoleMenu;
import cn.curleyg.entity.UserAuthorities;
import cn.curleyg.entity.UserRole;
import cn.curleyg.mapper.AuthRoleElementOperationMapper;
import cn.curleyg.mapper.AuthRoleMenuMapper;
import cn.curleyg.mapper.UserRoleMapper;
import cn.curleyg.service.IAuthRoleElementOperationService;
import cn.curleyg.service.IAuthRoleMenuService;
import cn.curleyg.service.IUserAuthService;
import cn.curleyg.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    IUserRoleService userRoleService;
    
    @Autowired
    IAuthRoleElementOperationService elementOperationService;
    
    @Autowired
    IAuthRoleMenuService authRoleMenuService;

    @Override
    public UserAuthorities getUserAuthorities(Long userId) {
        List<UserRole> userRoles = userRoleService.getUserRoleListByUserId(userId);
        Set<Long> roleIdSet = userRoles.stream().map(UserRole :: getRoleId).collect(Collectors.toSet());
        List<AuthRoleElementOperation> roleElementOperationList = elementOperationService.getRoleElementOperationsByRoleIds(roleIdSet);
        List<AuthRoleMenu> authRoleMenus = authRoleMenuService.getRoleMenuByRoleIds(roleIdSet);
        UserAuthorities userAuthorities = new UserAuthorities();
        userAuthorities.setRoleElementOperationList(roleElementOperationList);
        userAuthorities.setRoleMenuList(authRoleMenus);
        return userAuthorities;
    }
}
