package cn.curleyg.mapper;

import cn.curleyg.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户基本信息表 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2022-05-14
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
