package cn.curleyg.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
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
public enum StatusEnum {
    USE(1, "使用"),
    FORBIDDEN(2, "禁用"),
    LOGOUT(3,"注销");

    @EnumValue //标注哪一个字段是数据库里的字段
    private final int id;
    @JsonValue //标注要开启自定义序列化返回值；
    private final String msg;

    @Override
    public String toString() {
        return this.msg;
    }


}