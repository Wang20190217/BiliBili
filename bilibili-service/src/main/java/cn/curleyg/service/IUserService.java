package cn.curleyg.service;

import cn.curleyg.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wang
 * @since 2022-05-13
 */
public interface IUserService extends IService<User> {

    void addUser(User user);

    String login(User user) throws Exception;

    User getUserInfo(Long id);
}
