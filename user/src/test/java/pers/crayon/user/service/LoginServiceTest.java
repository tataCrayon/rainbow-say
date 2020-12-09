package pers.crayon.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.crayon.user.service.serviceimpl.LoginServiceImpl;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/10 0:03
 * @since JDK 1.8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    private static LoginServiceImpl loginService;

    @Test
    void getUserByUserName() {
        System.out.println("---------------------------------------");
        System.out.println(loginService.getUserByName("admin").getUserName());
        System.out.println("---------------------------------------");
    }

}
