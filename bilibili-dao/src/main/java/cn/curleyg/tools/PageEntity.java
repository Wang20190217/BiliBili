package cn.curleyg.tools;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author: Wang
 * @since: 2022/5/15 20:44 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageEntity implements Serializable {


    private static final long serialVersionUID = -1L;

    /**
     * 分页页码
     */
    @TableField(exist = false)
    private Integer pageNum;
    /**
     * 每页条数
     */
    @TableField(exist = false)
    private Integer pageSize;
    /**
     * 模糊查询 字符串
     */
    @TableField(exist = false)
    private String searchStr;
    /**
     * 开始时间字符串
     */
    @TableField(exist = false)
    private String start;
    /**
     * 结束时间字符串
     */
    @TableField(exist = false)
    private String end;



}
