package pers.crayon.user.annotation;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/27 15:59
 * @since JDK 1.8
 */

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/27 15:54
 * @since JDK 1.8
 * <p>需要验证</p>
 */
public @interface MustToken {
    boolean required() default true;
}
