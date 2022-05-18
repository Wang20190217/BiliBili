package cn.curleyg.controller;

import cn.curleyg.entity.UserInfo;
import cn.curleyg.service.IUserInfoService;
import cn.curleyg.support.Support;
import cn.curleyg.tools.PageEntity;
import cn.curleyg.tools.ResponseObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private IUserInfoService userInfoService;

    @Autowired
    private Support support;

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

     /***
      * @description: 根据用户昵称查询用户,已关注（未关注）区别
      * @param: [userInfo]
      * @since: 2022/5/15 20:53
     */
    @GetMapping("/user-info")
    public ResponseObject queryUserInfo(@RequestParam Integer pageNum,@RequestParam Integer pageSize,String search) {
        //从token中拿用户id
        Long currentUserId = support.getCurrentUserId();
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageNum(pageNum);
        pageEntity.setPageSize(pageSize);
        pageEntity.setSearchStr(search);
        IPage<UserInfo> infoIPage = userInfoService.queryUserInfo(pageEntity,currentUserId);
        return ResponseObject.success(infoIPage);
    }
}
