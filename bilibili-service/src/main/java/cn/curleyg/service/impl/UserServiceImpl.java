package cn.curleyg.service.impl;

import cn.curleyg.entity.User;
import cn.curleyg.exception.ConditionException;
import cn.curleyg.mapper.UserMapper;
import cn.curleyg.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wang
 * @since 2022-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        String phone = user.getPhone();
        if(StringUtils.isEmpty(phone)){
            throw new ConditionException("手机号不能为!");
        }

        //查询数据库手机号是否已经注册
       //TODO 因为账户可以注销要加判断，

    }
}
