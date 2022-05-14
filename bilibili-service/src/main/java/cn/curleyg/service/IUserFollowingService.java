package cn.curleyg.service;

import cn.curleyg.entity.UserFollowing;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
public interface IUserFollowingService extends IService<UserFollowing> {
    public void addUserFollowing(UserFollowing userFollowing);
}
