package cn.curleyg.service.impl;

import cn.curleyg.entity.FollowingGroup;
import cn.curleyg.enums.TypeEnum;
import cn.curleyg.mapper.FollowingGroupMapper;
import cn.curleyg.service.IFollowingGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户关注分组表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
@Service
public class FollowingGroupServiceImpl extends ServiceImpl<FollowingGroupMapper, FollowingGroup> implements IFollowingGroupService {
    @Autowired
    private FollowingGroupMapper followingGroupMapper;

    @Override
    public Long addFollowingGroup(FollowingGroup followingGroup) {
        followingGroup.setType(TypeEnum.USERDEFINED);
        followingGroup.setCreateTime(new Date());
        followingGroup.setDeleted("0");
        followingGroupMapper.insert(followingGroup);
        return followingGroup.getId();
    }

    /***
     * @description: 查出所有记录组
     * @param: [userId]
     * @since: 2022/5/15 22:39
     */
    @Override
    public List<FollowingGroup> getUserGroup(Long userId) {
        QueryWrapper<FollowingGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<FollowingGroup> followingGroupList = followingGroupMapper.selectList(queryWrapper);
        return followingGroupList;
    }

}
