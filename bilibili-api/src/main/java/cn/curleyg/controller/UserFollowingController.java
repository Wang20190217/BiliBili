package cn.curleyg.controller;


import cn.curleyg.entity.UserFollowing;
import cn.curleyg.service.IUserFollowingService;
import cn.curleyg.tools.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
    IUserFollowingService userFollowingService;

    @PostMapping("/user-following")
    public ResponseObject addUserFollowing(@RequestBody UserFollowing userFollowing){
        userFollowingService.addUserFollowing(userFollowing);
        return ResponseObject.success("关注成功");
    }
}
