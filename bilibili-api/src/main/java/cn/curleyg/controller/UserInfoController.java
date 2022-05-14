package cn.curleyg.controller;

import cn.curleyg.entity.UserInfo;
import cn.curleyg.service.IUserInfoService;
import cn.curleyg.support.Support;
import cn.curleyg.tools.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户基本信息表 前端控制器
 * </p>
 *
 * @author wang
 * @since 2022-05-14
 */
@RestController
public class UserInfoController {
    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    Support support;

    /***
     * @description: 修改用户登录信息
     * @param: [user]
     * @since: 2022/5/14 20:43
     */
    @PutMapping("/user-info")
    public ResponseObject updateUserInfo(@RequestBody UserInfo userInfo) {
        //从token中拿用户id
        Long currentUserId = support.getCurrentUserId();
        userInfo.setUserId(currentUserId);
        userInfoService.updateUserInfo(userInfo);
        return ResponseObject.success();
    }

}
