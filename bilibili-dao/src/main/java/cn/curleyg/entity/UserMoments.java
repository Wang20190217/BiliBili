package cn.curleyg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户动态表
 * </p>
 *
 * @author Wang
 * @since 2022-05-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_moments")
public class UserMoments implements Serializable {

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
     * 动态类型：0视频 1直播 2专栏动态
     */
    private String type;

    /**
     * 内容详情id
     */
    private Long contentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
