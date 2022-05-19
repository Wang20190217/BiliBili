package cn.curleyg.mapper;

import cn.curleyg.entity.AuthRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限控制--角色页面菜单关联表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Mapper
public interface AuthRoleMenuMapper extends BaseMapper<AuthRoleMenu> {

    List<AuthRoleMenu> getRoleMenuByRoleIds(Set<Long> roleIdSet);
}
