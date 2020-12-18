package pers.crayon.works.constant;

import java.time.format.DateTimeFormatter;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/9/23 9:32
 * @since JDK 1.8
 * <p>
 * 常量类接口，定义全局变量
 */
public interface Constant {
    /**
     * 系统序列化反序列化的时间格式
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    String DEFAULT_PAGE_NUM = "0";

    String DEFAULT_PAGE_SIZE = "10";
}
