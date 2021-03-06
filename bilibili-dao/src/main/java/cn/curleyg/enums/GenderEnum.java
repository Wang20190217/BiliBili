package cn.curleyg.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 城南花已开<br>
 *
 * @Description: 性别枚举类<br>
 * @Project: <br>
 * @CreateDate: Created in 2022/5/14 01:41 <br>
 * @Author: Wang
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE(1, "男"),
    FEMALE(2, "女"),
    UNKNOWN(3,"未知");

    @EnumValue //标注哪一个字段是数据库里的字段
    private final int id;
    private final String name;




}