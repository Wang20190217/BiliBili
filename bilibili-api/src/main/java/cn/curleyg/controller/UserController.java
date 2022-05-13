package cn.curleyg.controller;


import cn.curleyg.entity.User;
import cn.curleyg.service.IUserService;
import cn.curleyg.tools.ResponseObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import freemarker.template.utility.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/users")
    public ResponseObject addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseObject.success("创建成功");
    }

}
