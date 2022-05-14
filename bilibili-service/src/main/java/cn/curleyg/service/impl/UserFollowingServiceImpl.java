package cn.curleyg.service.impl;

import cn.curleyg.entity.FollowingGroup;
import cn.curleyg.entity.User;
import cn.curleyg.entity.UserFollowing;
import cn.curleyg.enums.TypeEnum;
import cn.curleyg.exception.ConditionException;
import cn.curleyg.mapper.FollowingGroupMapper;
import cn.curleyg.mapper.UserFollowingMapper;
import cn.curleyg.service.IUserFollowingService;
import cn.curleyg.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Watchable;
import java.util.Date;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
@Service
public class UserFollowingServiceImpl extends ServiceImpl<UserFollowingMapper, UserFollowing> implements IUserFollowingService {
    @Autowired
    private UserFollowingMapper userFollowingMapper;

    @Autowired
    private FollowingGroupMapper followingGroupMapper;

    @Autowired
    private IUserService userService;

    /***
     * @description: 添加一条关注信息
     * @param: [userFollowing]
     * @since: 2022/5/15 1:10
     */
    @Override
    @Transactional
    public void addUserFollowing(UserFollowing userFollowing) {
        Long groupId = userFollowing.getGroupId();
        //判断是否传关注组id
        if (groupId == null) {
            FollowingGroup followingGroup = followingGroupMapper.selectOne(new QueryWrapper<FollowingGroup>().eq("type", TypeEnum.DEFAULT.getId()));
            userFollowing.setGroupId(followingGroup.getId());
        }else {
            FollowingGroup followingGroup = followingGroupMapper.selectById(groupId);
            if(followingGroup==null){
                throw new ConditionException("分组不存在");
            }
        }
        //判断关注这个人id是否存在
        Long followingId = userFollowing.getFollowingId();
        User user = userService.getById(followingId);
        if(user==null){
            throw new ConditionException("关注用户不存在");
        }
        userFollowing.setCreateTime(new Date());
        userFollowing.setDeleted("0");
        //添加之前删除之前的关联关系
        QueryWrapper<UserFollowing> queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userFollowing.getUserId())
                .eq("group_id",userFollowing.getGroupId());
        userFollowingMapper.delete(queryWrapper);
        userFollowingMapper.insert(userFollowing);

    }
}
