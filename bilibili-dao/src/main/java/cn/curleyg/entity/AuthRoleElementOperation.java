package cn.curleyg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 权限控制--角色与元素操作关联表
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_auth_role_element_operation")
public class AuthRoleElementOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 元素操作id
     */
    private Long elementOperationId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 页面元素操作表
     */
    private  AuthElementOperation authElementOperation;

}
