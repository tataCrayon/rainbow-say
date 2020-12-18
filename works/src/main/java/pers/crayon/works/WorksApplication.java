package pers.crayon.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Import导入组件；在启动类时自定义扫描 @ServletComponentScan("pack.pack.servlet")
public class WorksApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorksApplication.class, args);
    }

}
