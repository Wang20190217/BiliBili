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
 * 权限控制--页面元素操作表
 * </p>
 *
 * @author Wang
 * @since 2022-05-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_auth_element_operation")
public class AuthElementOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 页面元素名称
     */
    private String elementName;

    /**
     * 页面元素唯一编码
     */
    private String elementCode;

    /**
     * 操作类型：0可点击  1可见
     */
    private String operationType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
