package cn.curleyg;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;


/**
 * 城南花已开<br>
 *
 * @Description: 代码生成器<br>
 * @Project: <br>
 * @CreateDate: Created in 2022/5/11 20:06 <br>
 * @Author: Wang
 */
public class GeneratorMain {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/bilibili?&useSSL=false&serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Wang") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\project\\BiliBili\\bilibili-dao\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.curleyg") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\project\\BiliBili\\bilibili-dao\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user_role","t_auth_role_menu","t_auth_element_operation","t_auth_menu","t_auth_role","t_auth_role_element_operation") // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }



}