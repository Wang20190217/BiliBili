package cn.curleyg.service.impl;

import cn.curleyg.entity.UserInfo;
import cn.curleyg.mapper.UserInfoMapper;
import cn.curleyg.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息表 服务实现类
 * </p>
 *
 * @author wang
 * @since 2022-05-14
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
}
