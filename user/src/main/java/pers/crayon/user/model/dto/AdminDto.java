package pers.crayon.user.model.dto;

import lombok.Data;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/11/10 14:40
 * @since JDK 1.8
 * <p>
 * 为什么要用Data注释实现，而不是使用反射方法直接获取呢
 */
@Data
public class AdminDto {

    /**
     * 管理员名称
     */
    private String name;

    /**
     * 管理员帐号密码
     */
    private String password;

}
