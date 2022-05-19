package cn.curleyg.controller;


import cn.curleyg.annotation.ApiLimitedRole;
import cn.curleyg.constant.AuthRoleConstant;
import cn.curleyg.entity.User;
import cn.curleyg.service.IUserService;
import cn.curleyg.support.Support;
import cn.curleyg.tools.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wang
 * @since 2022-05-13
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private Support support;

    /***
     * @description: 用户注册
     * @param: [user]
     * @since: 2022/5/14 18:48
     */
    @PostMapping("/users")
    public ResponseObject addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseObject.success("创建成功");
    }

    /***
     * @description: 获取登录令牌，实现登录
     * @param: [user]
     * @since: 2022/5/14 18:48
     */
    @PostMapping("/login")
    public ResponseObject login(@RequestBody User user) throws Exception {
        String token = userService.login(user);
        return ResponseObject.success(token, "登陆成功");
    }

    /***
     * @description: 获取当前登录的用户信息
     * @param: [user]
     * @since: 2022/5/14 18:48
     */
    @GetMapping("/users")
    public ResponseObject getUserInfo() {
        Long currentUserId = support.getCurrentUserId();
        User userInfo = userService.getUserInfo(currentUserId);
        return ResponseObject.success(userInfo);
    }

}
