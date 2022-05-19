package cn.curleyg.aspect;

import cn.curleyg.constant.AuthRoleConstant;
import cn.curleyg.entity.UserMoments;
import cn.curleyg.entity.UserRole;
import cn.curleyg.exception.ConditionException;
import cn.curleyg.service.IUserRoleService;
import cn.curleyg.support.Support;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Order(1)
@Component
@Aspect
public class DataLimitedAspect {

    @Autowired
    private Support userSupport;

    @Autowired
    private IUserRoleService userRoleService;

    @Pointcut("@annotation(cn.curleyg.annotation.DataLimited)")
    public void check(){
    }

    @Before("check()")
    public void doBefore(JoinPoint joinPoint){
        Long userId = userSupport.getCurrentUserId();
        List<UserRole> userRoleList = userRoleService.getUserRoleListByUserId(userId);
        Set<String> roleCodeSet = userRoleList.stream().map(UserRole::getRoleCode).collect(Collectors.toSet());
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
          if(arg instanceof UserMoments){
              UserMoments userMoment = (UserMoments)arg;
              String type = userMoment.getType();
              if(roleCodeSet.contains(AuthRoleConstant.ROLE_LV1) && !"0".equals(type)){
                  throw new ConditionException("参数异常");
              }
          }
        }
    }
}
