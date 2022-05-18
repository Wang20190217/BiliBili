package cn.curleyg.service;

import cn.curleyg.entity.FollowingGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户关注分组表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
public interface IFollowingGroupService extends IService<FollowingGroup> {

    Long addFollowingGroup(FollowingGroup followingGroup);

    List<FollowingGroup> getUserGroup(Long userId);
}
