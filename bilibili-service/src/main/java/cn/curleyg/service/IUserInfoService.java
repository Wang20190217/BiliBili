package cn.curleyg.service;

import cn.curleyg.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基本信息表 服务类
 * </p>
 *
 * @author wang
 * @since 2022-05-14
 */
public interface IUserInfoService extends IService<UserInfo> {
    void updateUserInfo(UserInfo userInfo);

}
