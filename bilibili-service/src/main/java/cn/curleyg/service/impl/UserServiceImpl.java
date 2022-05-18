package cn.curleyg.service.impl;

import cn.curleyg.constant.UserConstant;
import cn.curleyg.entity.User;
import cn.curleyg.entity.UserInfo;
import cn.curleyg.enums.GenderEnum;
import cn.curleyg.enums.StatusEnum;
import cn.curleyg.exception.ConditionException;
import cn.curleyg.mapper.UserInfoMapper;
import cn.curleyg.mapper.UserMapper;
import cn.curleyg.service.IUserInfoService;
import cn.curleyg.service.IUserService;
import cn.curleyg.utils.MD5Util;
import cn.curleyg.utils.RSAUtil;
import cn.curleyg.utils.TokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


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
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private IUserInfoService  userInfoService;

    @Override
    public void addUser(User user) {
        String phone = user.getPhone();
        if (StringUtils.isEmpty(phone)) {
            throw new ConditionException("手机号不能为!");
        }
        //查询数据库手机号是否已经注册
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone).ne("status", StatusEnum.LOGOUT.getId());
        List<User> userList = userMapper.selectList(wrapper);
        if (userList.size() > 0) {
            throw new ConditionException("手机号已被注册!");
        }
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);//对密码解密
        } catch (Exception e) {
            throw new ConditionException("密码解密失败!");
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(now);
        user.setStatus(StatusEnum.USE);
        userMapper.insert(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK);
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH);
        userInfo.setGender(GenderEnum.UNKNOWN);
        userInfo.setCreateTime(now);
        userInfoMapper.insert(userInfo);
    }

    @Override
    public String login(User user) throws Exception {
        //对密码进行解密
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);//对密码解密
        } catch (Exception e) {
            throw new ConditionException("密码解密失败!");
        }
        //数据库的比对
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ne("status", StatusEnum.LOGOUT.getId());
        Boolean phoneNull = StringUtils.isEmpty(user.getPhone());
        Boolean emailNull = StringUtils.isEmpty(user.getEmail());
        if (!phoneNull) {
            wrapper.eq("phone", user.getPhone());
        }
        if (!emailNull) {
            wrapper.eq("email", user.getEmail());
        }
        if (phoneNull && emailNull) {
            throw new ConditionException("请使用手机号或者邮箱登录");
        }
        User dbUser = userMapper.selectOne(wrapper);
        if (dbUser == null) {
            throw new ConditionException("账号不存在");
        }
        String md5Password = MD5Util.sign(rawPassword, dbUser.getSalt(), "UTF-8");
        if (!md5Password.equals(dbUser.getPassword())) {
            throw new ConditionException("密码错误");
        }
        //生成用户令牌
        return TokenUtil.generateToken(dbUser.getId());

    }

    @Override
    public User getUserInfo(Long id) {
        User user = userMapper.selectById(id);
        user.setUserInfo(userInfoService.getUserInfoByUserId(id));
        return user;
    }
}
