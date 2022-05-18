package cn.curleyg.controller;

import cn.curleyg.entity.UserAuthorities;
import cn.curleyg.service.IUserAuthService;
import cn.curleyg.support.Support;
import cn.curleyg.tools.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     权限接口
 * </p>
 *
 * @author: Wang
 * @since: 2022/5/18 12:20 <br>
 */
@RestController
public class UserAuthController {
    @Autowired
    Support support;
    @Autowired
    IUserAuthService userAuthService;

    @GetMapping("/user-authorities")
    public ResponseObject getUserAuthorities(){
        Long userId = support.getCurrentUserId();
        UserAuthorities userAuthorities = userAuthService.getUserAuthorities(userId);
        return ResponseObject.success(userAuthorities);
    }

}
