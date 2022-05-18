package cn.curleyg.service;

import cn.curleyg.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
public interface IUserRoleService extends IService<UserRole> {
    //根据userid查询角色列表
    List<UserRole> getUserRoleListByUserId(Long userId);
}
