package cn.curleyg.service;

import cn.curleyg.entity.UserMoments;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户动态表 服务类
 * </p>
 *
 * @author Wang
 * @since 2022-05-16
 */
public interface IUserMomentsService extends IService<UserMoments> {

    void addUserMoments(UserMoments userMoments) throws Exception;

    List<UserMoments> getUserSubscribedMoments(Long userId);
}
