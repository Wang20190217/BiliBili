package cn.curleyg.entity;

import cn.curleyg.tools.PageEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户关注表
 * </p>
 *
 * @author Wang
 * @since 2022-05-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_following")
public class UserFollowing extends PageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关注用户id
     */
    private Long followingId;

    /**
     * 关注分组id
     */
    private Long groupId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 删除标记
     */
    @TableLogic
    private String deleted;

    @TableField(exist = false)
    private UserInfo userInfo;

}
