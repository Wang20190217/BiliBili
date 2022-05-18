package cn.curleyg.service.impl;

import cn.curleyg.constant.UserConstant;
import cn.curleyg.entity.FollowingGroup;
import cn.curleyg.entity.User;
import cn.curleyg.entity.UserFollowing;
import cn.curleyg.entity.UserInfo;
import cn.curleyg.enums.TypeEnum;
import cn.curleyg.exception.ConditionException;
import cn.curleyg.mapper.FollowingGroupMapper;
import cn.curleyg.mapper.UserFollowingMapper;
import cn.curleyg.service.IUserFollowingService;
import cn.curleyg.service.IUserInfoService;
import cn.curleyg.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


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

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserFollowingService userFollowingService;


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
        } else {
            FollowingGroup followingGroup = followingGroupMapper.selectById(groupId);
            if (followingGroup == null) {
                throw new ConditionException("分组不存在");
            }
        }
        //判断关注这个人id是否存在
        Long followingId = userFollowing.getFollowingId();
        User user = userService.getById(followingId);
        if (user == null) {
            throw new ConditionException("关注用户不存在");
        }
        userFollowing.setCreateTime(new Date());
        userFollowing.setDeleted("0");
        //添加之前删除之前的关联关系
        QueryWrapper<UserFollowing> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userFollowing.getUserId())
                .eq("group_id", userFollowing.getGroupId());
        userFollowingMapper.delete(queryWrapper);
        userFollowingMapper.insert(userFollowing);
    }

    /***
     * @description: 根据用户id查询关注列表
     * @param: [userId]
     * @since: 2022/5/15 3:49
     */
    @Override
    public List<FollowingGroup> getUserFollowList(Long userId) {
        //1.查出所有关注信息 list
        List<UserFollowing> list = userFollowingService.getUserFollowingByUserId(userId);
        //2.根据关注信息的关注人id,获取所有关注人的信息 userList
        List<UserInfo> userList = new ArrayList<>();
        if (list.size() > 0) {
            for (UserFollowing userFollowing : list) {
                userList.add(userInfoService.getUserInfoByUserId(userFollowing.getFollowingId()));
            }
        }
        //将个人信息绑定到关注信息上
        for (UserFollowing userFollowing : list) {
            for (UserInfo userInfo : userList) {
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())) {
                    userFollowing.setUserInfo(userInfo);
                }
            }
        }
        //根据userid获取用户所有关注组，而且默认包含3个分组
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(TypeEnum.SPECIAL.getId());
        arrayList.add(TypeEnum.QUIETLY.getId());
        arrayList.add(TypeEnum.DEFAULT.getId());
        QueryWrapper<FollowingGroup> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).or().in("type", arrayList);
        List<FollowingGroup> followingGroupList = followingGroupMapper.selectList(wrapper);
        //全部关注分组
        FollowingGroup allGroup = new FollowingGroup();
        allGroup.setName(UserConstant.USER_FOLLOWING_GROUP_ALL_NAME);
        allGroup.setUserList(userList);
        List<FollowingGroup> result = new ArrayList<>();
        result.add(allGroup);
        for (FollowingGroup group : followingGroupList) {
            List<UserInfo> infoList = new ArrayList<>();
            for (UserFollowing userFollowing : list) {
                if (group.getId().equals(userFollowing.getGroupId())) {
                    infoList.add(userFollowing.getUserInfo());
                }

            }
            group.setUserList(infoList);
            result.add(group);
        }
        return result;
    }

    /**
     * @description: 查询粉丝列表，判断是否共同关注
     * @param: [userId]
     * @since: 2022/5/15 22:58
     */
    public List<UserFollowing> getUserFans(Long userId) {
        //1.查出所有被关注人是userid的关注信息 list
        List<UserFollowing> list = userFollowingService.getUserFollowingByFollowingId(userId);
        List<UserInfo> userList = new ArrayList<>();
        //根据关注人userid查询基本信息
        if (list.size() > 0) {
            for (UserFollowing userFollowing : list) {
                UserInfo userInfo = userInfoService.getUserInfoByUserId(userFollowing.getUserId());
                userList.add(userInfo);
            }
        }
        //获取用户关注的列表进行比对查出共同关注
        QueryWrapper<UserFollowing> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<UserFollowing> followingList = userFollowingMapper.selectList(wrapper);
        for (UserFollowing fan : list) {
            for (UserInfo userInfo : userList) {
                if (fan.getUserId().equals(userInfo.getUserId())) {
                    userInfo.setFollowed(false);
                    fan.setUserInfo(userInfo);
                }
            }
            for (UserFollowing following : followingList) {
                if (following.getFollowingId().equals(fan.getUserId())) {
                    fan.getUserInfo().setFollowed(true);
                }
            }
        }
        return list;
    }

    /**
     * @description: 检验用户集合是否用户关注过
     * @param: [userid, list]
     * @since: 2022/5/15 23:14
    */
    @Override
    public List<UserInfo> checkFollowingStatus(Long userid, List<UserInfo> list) {
        //查出关注了那些用户
        List<UserFollowing> userFollowingList = userFollowingService.getUserFollowingByUserId(userid);
        for (UserInfo userInfo : list) {
            userInfo.setFollowed(false);
            for (UserFollowing userFollowing : userFollowingList) {
                if (userInfo.getUserId().equals(userFollowing.getFollowingId())) {
                    userInfo.setFollowed(true);
                }
            }
        }
        return list;
    }

    /**
     * @description: 根据userid 查询关注人是我的关注信息
     * @param: [userId]
     * @since: 2022/5/15 22:45
     */
    public List<UserFollowing> getUserFollowingByFollowingId(Long followingId) {
        //1.查出所有被关注人是userid的关注信息 list
        QueryWrapper<UserFollowing> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("following_id", followingId);
        List<UserFollowing> list = userFollowingMapper.selectList(queryWrapper);
        return list;
    }

    /**
     * @description: 根据userid 查询所有的关注信息
     * @param: [userId]
     * @since: 2022/5/15 22:45
     */
    @Override
    public List<UserFollowing> getUserFollowingByUserId(Long userId) {
        //1.查出所有被关注人是userid的关注信息 list
        QueryWrapper<UserFollowing> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserFollowing> list = userFollowingMapper.selectList(queryWrapper);
        return list;
    }

}
