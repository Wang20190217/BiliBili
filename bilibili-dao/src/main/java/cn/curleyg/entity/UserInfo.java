package cn.curleyg.entity;

import cn.curleyg.enums.GenderEnum;
import cn.curleyg.tools.PageEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 用户基本信息表
 * </p>
 *
 * @author wang
 * @since 2022-05-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_info")
public class UserInfo  extends PageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 签名
     */
    private String sign;

    /**
     * 性别：0男 1女 2未知
     */
    private GenderEnum gender;

    /**
     * 生日
     */
    private String birth;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否共同关注
     */
    @TableField(exist = false)
    private Boolean followed;

}
