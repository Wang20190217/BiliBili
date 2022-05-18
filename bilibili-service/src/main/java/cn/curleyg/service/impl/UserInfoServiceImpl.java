package cn.curleyg.service.impl;

import cn.curleyg.entity.UserInfo;
import cn.curleyg.mapper.UserInfoMapper;
import cn.curleyg.service.IUserFollowingService;
import cn.curleyg.service.IUserInfoService;
import cn.curleyg.tools.PageEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private IUserFollowingService userFollowingService;


    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfo.setUpdateTime(new Date());
        QueryWrapper<UserInfo> objectQueryWrapper = new QueryWrapper<>();
        userInfoMapper.update(userInfo, objectQueryWrapper.eq("user_id", userInfo.getUserId()));
    }

    /***
     * @description: 根据userid获得userInfo信息
     * @param: []
     * @since: 2022/5/15 4:53
     */
    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<UserInfo> queryUserInfo(PageEntity pageEntity,Long userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
        queryWrapper.like("nick", pageEntity.getSearchStr());
        Page<UserInfo> userInfoPage = userInfoMapper.selectPage(new Page<>(pageEntity.getPageNum(), pageEntity.getPageSize()), queryWrapper);
        if(userInfoPage.getSize()>0){
            List<UserInfo> userInfos = userFollowingService.checkFollowingStatus(userId,userInfoPage.getRecords());
            userInfoPage.setRecords(userInfos);
        }
        //查出所有的粉丝
        return userInfoPage;
    }
}
