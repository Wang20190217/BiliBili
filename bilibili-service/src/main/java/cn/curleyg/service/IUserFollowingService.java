package cn.curleyg.service;

import cn.curleyg.entity.FollowingGroup;
import cn.curleyg.entity.UserFollowing;
import cn.curleyg.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
public interface IUserFollowingService extends IService<UserFollowing> {
    void addUserFollowing(UserFollowing userFollowing);

    List<FollowingGroup> getUserFollowList(Long userId);

    List<UserFollowing> getUserFans(Long userId);

    List<UserInfo> checkFollowingStatus(Long id, List<UserInfo> list);

    List<UserFollowing> getUserFollowingByFollowingId(Long userId);

    List<UserFollowing> getUserFollowingByUserId(Long userId);
}
