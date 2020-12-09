package pers.crayon.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/11/25 11:46
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mail.xml")
public class MailTest {
    @Resource
    private JavaMailSenderImpl mailSender;
    @Resource
    private SimpleMailMessage mailMessage;

    @Test
    public void send() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date date = new Date();
        mailMessage.setTo("910368916@qq.com");
        mailMessage.setSubject("邮件测试");
        mailMessage.setText("测试时间：" + sdf.format(date));
        mailSender.send(mailMessage);
    }
}
