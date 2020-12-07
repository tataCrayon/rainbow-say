package pers.crayon.user.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/7 11:18
 * @since JDK 1.8
 * <p>
 * 用于 生成文档 的自定义策略注解
 */
@Target(ElementType.METHOD) // 注解生效域
@Retention(RetentionPolicy.RUNTIME) // 注解运行策略
public @interface NotIncludeSwagger {

}
