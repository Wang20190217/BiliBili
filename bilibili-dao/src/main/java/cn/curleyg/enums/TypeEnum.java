package cn.curleyg.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 城南花已开<br>
 *
 * @Description: 关注类型<br>
 * @Project: <br>
 * @CreateDate: Created in 2022/5/14 01:41 <br>
 * @Author: Wang
 */
@Getter
@AllArgsConstructor
public enum TypeEnum {
    SPECIAL(1, "特殊关注"),
    QUIETLY(2, "悄悄关注"),
    DEFAULT(3,"默认分组"),
    USERDEFINED(4,"自定义分组");

    @EnumValue //标注哪一个字段是数据库里的字段
    private final int id;
    private final String msg;



}