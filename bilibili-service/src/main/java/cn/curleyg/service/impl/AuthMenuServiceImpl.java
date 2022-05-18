package cn.curleyg.service.impl;

import cn.curleyg.entity.AuthMenu;
import cn.curleyg.mapper.AuthMenuMapper;
import cn.curleyg.service.IAuthMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限控制-页面访问表 服务实现类
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Service
public class AuthMenuServiceImpl extends ServiceImpl<AuthMenuMapper, AuthMenu> implements IAuthMenuService {

}
