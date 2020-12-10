package pers.crayon.user.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import pers.crayon.user.config.MailConfig;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/11/26 9:53
 * @since JDK 1.8
 * <p>
 * 邮件配置发送工具
 */
@PropertySource(value = "classpath:mail.properties")
public class MailUtil {
    // 可动态配置信息

    @Value("{mail.host}")
    private static String host;
    @Value("{mail.port}")
    private static int port;
    @Value("{mail.username}")
    private static String userName;
    @Value("{mail.password}")
    private static String password;
    @Value("{mail.smtp.timeout}")
    private static int timeout;
    @Value("{mail.from}")
    private static String from;

    public static JavaMailSenderImpl mailSender = createMailSender(MailConfig.mail_host, MailConfig.mail_port, MailConfig.mail_username,
            MailConfig.mail_password, MailConfig.mail_smtp_timeout);
    public static String mailFrom = MailConfig.mail_from;

    private static JavaMailSenderImpl createMailSender(String host, int port, String username, String password, int timeout) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", timeout + "");
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;
    }

    //发送测试的邮件
    public static void sendMailForTest(String host, int port, String username, String password, String from,
                                       String to) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject("这是测试邮件，请勿回复！");
        mail.setSentDate(new Date());// 邮件发送时间
        mail.setText("这是一封测试邮件。如果您已收到此邮件，说明您的邮件服务器已设置成功。请勿回复，请勿回复，请勿回复，重要的事说三遍！");
        JavaMailSenderImpl sender = createMailSender(host, port, username, password, 25000);
        sender.send(mail);
    }

    public static void sendTextMail(String to, String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(mailFrom);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setSentDate(new Date());// 邮件发送时间
        mail.setText(text);
        mailSender.send(mail);
    }

    public static void sendHtmlMail(String to, String subject, String html) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    public static void sendFileMail(String to, String subject, String html, String contentId, Resource resource) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        //FileSystemResource img = new FileSystemResource(new File("c:/350.jpg"));
        messageHelper.addInline(contentId, resource);
        // 发送
        mailSender.send(mimeMessage);
    }
}
