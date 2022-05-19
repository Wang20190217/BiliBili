package cn.curleyg.service;

import cn.curleyg.entity.AuthRoleElementOperation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限控制--角色与元素操作关联表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
public interface IAuthRoleElementOperationService extends IService<AuthRoleElementOperation> {

    List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(Set<Long> roleIdSet);
}
