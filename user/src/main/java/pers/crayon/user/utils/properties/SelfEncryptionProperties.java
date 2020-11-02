package pers.crayon.user.utils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/20 16:25
 * @since JDK 1.8
 * <p>
 * 将配置参数封装对象化,duck不必的麻烦
 * * 同@Value功能
 */
@Data
@Component
@ConfigurationProperties(prefix = "encryption")
public class SelfEncryptionProperties {
    /*
        所有参数都需要在类中属性化
        然后实例化引用属性化参数
         */
    private String MD5KEY;
}
