package pers.crayon.user.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * <p>
 *
 * @author tataCrayon
 * @version 1.0
 * @Target后面的参数，要手打，不然导入会出问题 </p>
 * @date 2020/9/24 17:25
 * @since JDK 1.8
 */
@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AnnotationTest {


}
