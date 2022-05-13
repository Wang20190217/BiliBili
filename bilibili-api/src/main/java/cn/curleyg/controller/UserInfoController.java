package cn.curleyg.controller;


import cn.curleyg.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户基本信息表 前端控制器
 * </p>
 *
 * @author wang
 * @since 2022-05-14
 */
@Controller
@RequestMapping("/user-info")
public class UserInfoController {
    @Autowired
    IUserInfoService userInfoService;

}
