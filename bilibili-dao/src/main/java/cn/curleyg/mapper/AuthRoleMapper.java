package cn.curleyg.mapper;

import cn.curleyg.entity.AuthRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限控制--角色表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Mapper
public interface AuthRoleMapper extends BaseMapper<AuthRole> {

}
