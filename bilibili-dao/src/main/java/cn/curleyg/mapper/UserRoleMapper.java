package cn.curleyg.mapper;

import cn.curleyg.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<UserRole> getUserRoleListByUserId(Long userId);
}
