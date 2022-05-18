package cn.curleyg.service.impl;

import cn.curleyg.entity.UserRole;
import cn.curleyg.mapper.UserRoleMapper;
import cn.curleyg.service.IUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> getUserRoleListByUserId(Long userId) {
        QueryWrapper<UserRole> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        return userRoles;
    }
}
