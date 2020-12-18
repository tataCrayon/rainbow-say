package pers.crayon.user;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/11 16:04
 * @since JDK 1.8
 */
@Component
public class ScheduledTest {


    @Scheduled(fixedDelay = 50000)
    void print5() {
        System.out.println("每隔五秒:${Date()}");
        ;
    }

}
