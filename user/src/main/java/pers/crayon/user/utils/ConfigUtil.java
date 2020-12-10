package pers.crayon.user.utils;


import pers.crayon.user.config.MailConfig;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/11/26 10:29
 * @since JDK 1.8
 * <p>
 * 修改配置文件信息，一般需要重启才会扫描配置信息并有效，可以使用 Spring的工具修改
 */
public class ConfigUtil {
    public static String getProperty(String key) throws IOException {
        return getProperty(MailConfig.PROPERTIES_DEFAULT, key);
    }

    public static Object setProperty(String propertyName, String propertyValue) throws URISyntaxException, IOException {
        return setProperty(MailConfig.PROPERTIES_DEFAULT, propertyName, propertyValue);
    }

    public static void setProperties(Set<Entry<String, Object>> data) throws IOException, URISyntaxException {
        setProperties(MailConfig.PROPERTIES_DEFAULT, data);
    }

    // 读取Properties的全部信息
    public static Map<String, String> getAllProperties() throws IOException {
        Properties pps = new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(MailConfig.PROPERTIES_DEFAULT);
        pps.load(in);
        in.close();
        Enumeration<?> en = pps.propertyNames(); // 得到配置文件的名字
        Map<String, String> map = new HashMap<String, String>();
        while (en.hasMoreElements()) {
            String strKey = en.nextElement().toString();
            map.put(strKey, pps.getProperty(strKey));
        }
        return map;
    }

    public static String getProperty(String filePath, String key) throws IOException {
        Properties pps = new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        pps.load(in);
        in.close();
        return pps.getProperty(key);
    }

    public static Object setProperty(String filePath, String propertyName, String propertyValue) throws URISyntaxException, IOException {
        Properties p = new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        p.load(in);//
        in.close();
        Object o = p.setProperty(propertyName, propertyValue);//设置属性值，如属性不存在新建
        OutputStream out = new FileOutputStream(new File(ConfigUtil.class.getClassLoader().getResource(MailConfig.PROPERTIES_DEFAULT).toURI()));//输出流
        p.store(out, "modify");//设置属性头，如不想设置，请把后面一个用""替换掉
        out.flush();//清空缓存，写入磁盘
        out.close();//关闭输出流
        MailConfig.initOrRefresh();//刷新缓存
        return o;
    }

    public static void setProperties(String filePath, Set<Entry<String, Object>> data) throws IOException, URISyntaxException {
        Properties p = new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        p.load(in);//
        in.close();
        for (Entry<String, Object> entry : data) { //先遍历整个 people 对象
            p.setProperty(entry.getKey(), entry.getValue().toString());//设置属性值，如属性不存在新建
        }
        OutputStream out = new FileOutputStream(new File(ConfigUtil.class.getClassLoader().getResource(MailConfig.PROPERTIES_DEFAULT).toURI()));//输出流
        p.store(out, "modify");//设置属性头，如不想设置，请把后面一个用""替换掉
        out.flush();//清空缓存，写入磁盘
        out.close();//关闭输出流
        MailConfig.initOrRefresh();//刷新缓存
    }
}
