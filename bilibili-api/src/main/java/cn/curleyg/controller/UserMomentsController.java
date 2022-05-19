package cn.curleyg.controller;


import cn.curleyg.annotation.ApiLimitedRole;
import cn.curleyg.constant.AuthRoleConstant;
import cn.curleyg.entity.UserMoments;
import cn.curleyg.service.IUserMomentsService;
import cn.curleyg.support.Support;
import cn.curleyg.tools.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户动态表 前端控制器
 * </p>
 *
 * @author Wang
 * @since 2022-05-16
 */
@RestController

public class UserMomentsController {
    @Autowired
    IUserMomentsService userMomentsService;

    @Autowired
    Support support;

    // TODO 发布存在状态，草稿  内容详情表
    @ApiLimitedRole(limitedRoleCodeList = {AuthRoleConstant.ROLE_LV1})
    @PostMapping("/user-moments")
    public ResponseObject addUserMoments(@RequestBody UserMoments userMoments) throws Exception {
        Long currentUserId = support.getCurrentUserId();
        userMoments.setUserId(currentUserId);
        userMomentsService.addUserMoments(userMoments);
        return ResponseObject.success("添加成功");
    }

    @GetMapping("/user-subscribed-moments")
    public  ResponseObject getUserSubscribedMoments(){
        Long userId = support.getCurrentUserId();
        List<UserMoments> list = userMomentsService.getUserSubscribedMoments(userId);
        return ResponseObject.success(list);
    }

}
