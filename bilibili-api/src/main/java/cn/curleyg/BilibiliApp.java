package cn.curleyg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement//启动注解事务管理
public class BilibiliApp {

    public static void main(String[] args){
        ApplicationContext app = SpringApplication.run(BilibiliApp.class, args);
//        WebSocketService.setApplicationContext(app);
    }

}
