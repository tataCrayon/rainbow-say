package pers.crayon.user.config;

import java.io.InputStream;
import java.util.Properties;

public class MailConfig {
    public static final String PROPERTIES_DEFAULT = "mail.properties";//类路径下的属性文件名
    //mail
    public static String mail_host;
    public static int mail_port;
    public static String mail_from;
    public static String mail_username;
    public static String mail_password;
    public static int mail_smtp_timeout;

    static {
        initOrRefresh();
    }

    //初始化或更新缓存
    public static void initOrRefresh() {
        Properties p = new Properties();
        try {
            InputStream in = pers.crayon.user.config.MailConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            p.load(in);
            in.close();
            mail_host = p.getProperty("mail.host", "smtp.qq.com");
            mail_port = Integer.parseInt(p.getProperty("mail.port", "25"));
            mail_from = p.getProperty("mail.from");
            mail_username = p.getProperty("mail.username");
            mail_password = p.getProperty("mail.password");
            mail_smtp_timeout = Integer.parseInt(p.getProperty("mail.smtp.timeout", "25000"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
