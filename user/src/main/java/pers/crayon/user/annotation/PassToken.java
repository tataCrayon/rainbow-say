package pers.crayon.user.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/27 15:54
 * @since JDK 1.8
 * <p>跳过验证</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface PassToken {

    boolean required() default true;

}
