package cn.curleyg.service;


import cn.curleyg.entity.UserAuthorities;

public interface IUserAuthService  {
    UserAuthorities getUserAuthorities(Long userId);
}
