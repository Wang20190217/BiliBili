package cn.curleyg.service;

import cn.curleyg.entity.AuthRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限控制--角色页面菜单关联表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
public interface IAuthRoleMenuService extends IService<AuthRoleMenu> {

    List<AuthRoleMenu> getRoleMenuByRoleIds(Set<Long> roleIdSet);
}
