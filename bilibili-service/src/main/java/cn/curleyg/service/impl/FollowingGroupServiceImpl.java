package cn.curleyg.service.impl;

import cn.curleyg.entity.FollowingGroup;
import cn.curleyg.mapper.FollowingGroupMapper;
import cn.curleyg.service.IFollowingGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
