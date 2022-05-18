package cn.curleyg.controller;


import cn.curleyg.entity.FollowingGroup;
import cn.curleyg.entity.UserFollowing;
import cn.curleyg.enums.TypeEnum;
import cn.curleyg.service.IFollowingGroupService;
import cn.curleyg.service.IUserFollowingService;
import cn.curleyg.support.Support;
import cn.curleyg.tools.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 用户关注分组表 前端控制器
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
@RestController
public class FollowingGroupController {
    @Autowired
    private IUserFollowingService userFollowingService;
    @Autowired
    private IFollowingGroupService followingGroupService;

    @Autowired
    Support support;


    /*
     * @description: 查询用户每一个关注分组列表中关注的用户信息
     * @param: []
     * @since: 2022/5/15 18:46
    */
    @GetMapping("/following-group-userinfo")
    public ResponseObject getFpllowingGroupUserInfo() {
        Long userId = support.getCurrentUserId();
        List<FollowingGroup> userFollowList = userFollowingService.getUserFollowList(userId);
        return ResponseObject.success(userFollowList);
    }


    /**
     * @description: 添加用户关注分组
     * @param: [followingGroup]
     * @since: 2022/5/15 18:46
    */
    @PostMapping("/following-group")
    public ResponseObject addUserGroup(@RequestBody FollowingGroup followingGroup) {
        Long userId = support.getCurrentUserId();
        followingGroup.setUserId(userId);
        Long groupId = followingGroupService.addFollowingGroup(followingGroup);
        return ResponseObject.success(groupId, "添加分组成功");
    }

    /*
     * @description: 查询用户所有分组
     * @param: []
     * @since: 2022/5/15 18:46
     */
    @GetMapping("/following-group")
    public ResponseObject getFpllowingGroup() {
        Long userId = support.getCurrentUserId();
        List<FollowingGroup> userFollowList = followingGroupService.getUserGroup(userId);
        return ResponseObject.success(userFollowList);
    }

}
