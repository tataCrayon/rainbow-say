package pers.crayon.user.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/9/23 9:34
 * @since JDK 1.8
 */
@AllArgsConstructor
@Getter
public enum ResponseCodeEnum {

    OK(200, "OK"),
    SYSTEM_EXCEPTION(70, "系统异常"), USER_OPERATE_ERROR(71, "用户操作错误"),
    USERNAME_OR_PWD_ERROR(72, "用户名或密码错误"), TOKEN_INVALID(73, "登录令牌无效"), HEALTH_CARD_EXCEPTION(74, "微信健康卡服务异常");

    private int code;
    private String desc;

}
