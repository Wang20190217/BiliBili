package cn.curleyg.mapper;

import cn.curleyg.entity.UserFollowing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户关注表 Mapper 接口
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
@Mapper
public interface UserFollowingMapper extends BaseMapper<UserFollowing> {


}
