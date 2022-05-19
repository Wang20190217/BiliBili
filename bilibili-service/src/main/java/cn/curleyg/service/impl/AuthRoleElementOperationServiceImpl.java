package cn.curleyg.service.impl;

import cn.curleyg.entity.AuthRoleElementOperation;
import cn.curleyg.mapper.AuthRoleElementOperationMapper;
import cn.curleyg.service.IAuthRoleElementOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 权限控制--角色与元素操作关联表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Service
public class AuthRoleElementOperationServiceImpl extends ServiceImpl<AuthRoleElementOperationMapper, AuthRoleElementOperation> implements IAuthRoleElementOperationService {

    @Autowired
    AuthRoleElementOperationMapper authRoleElementOperationMapper;
    @Override
    public List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(Set<Long> roleIdSet) {
        List<AuthRoleElementOperation> roleElementOperationsByRoleIds = authRoleElementOperationMapper.getRoleElementOperationsByRoleIds(roleIdSet);
        return roleElementOperationsByRoleIds;
    }
}
