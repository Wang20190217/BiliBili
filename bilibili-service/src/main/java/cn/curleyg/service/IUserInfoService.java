package cn.curleyg.service;

import cn.curleyg.entity.UserInfo;
import cn.curleyg.tools.PageEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
    UserInfo getUserInfoByUserId(Long id);

    IPage<UserInfo> queryUserInfo(PageEntity pageEntity,Long id);


}
