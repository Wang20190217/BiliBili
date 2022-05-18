package cn.curleyg.entity;

import cn.curleyg.enums.TypeEnum;
import cn.curleyg.tools.PageEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户关注分组表
 * </p>
 *
 * @author Wang
 * @since 2022-05-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_following_group")
public class FollowingGroup extends PageEntity implements Serializable {

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
     * 关注分组名称
     */
    private String name;
    //TODO 分组说明
    /**
     * 关注分组类型：0特别关注  1悄悄关注 2默认分组  3用户自定义分组
     */
    private TypeEnum type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标记
     */
    @TableLogic
    private String deleted;

    @TableField(exist=false)
    private List<UserInfo> userList;


}
