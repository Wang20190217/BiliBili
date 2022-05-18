package cn.curleyg.controller;


import cn.curleyg.entity.UserFollowing;
import cn.curleyg.service.IUserFollowingService;
import cn.curleyg.support.Support;
import cn.curleyg.tools.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户关注表 前端控制器
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
@RestController
public class UserFollowingController {
    @Autowired
    private IUserFollowingService userFollowingService;
    @Autowired
    private Support support;

    /**
     * @description: 添加关注
     * @param: [userFollowing]
     * @since: 2022/5/15 18:46
     */
    @PostMapping("/user-following")
    public ResponseObject addUserFollowing(@RequestBody UserFollowing userFollowing) {
        userFollowingService.addUserFollowing(userFollowing);
        return ResponseObject.success("关注成功");
    }

    /**
     * @description: 查询所有粉丝的个人信息
     * @param: []
     * @since: 2022/5/15 18:46
    */
    @GetMapping("/fanlist")
    public ResponseObject getFanList() {
        Long userId = support.getCurrentUserId();
        List<UserFollowing> userFansList = userFollowingService.getUserFans(userId);
        return ResponseObject.success(userFansList);
    }
}
