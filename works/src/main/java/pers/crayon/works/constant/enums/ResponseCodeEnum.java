package pers.crayon.works.constant.enums;

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
    USERNAME_OR_PWD_ERROR(72, "用户名或密码错误"), TOKEN_INVALID(73, "登录令牌无效"),
    HEALTH_CARD_EXCEPTION(74, "微信健康卡服务异常"),
    MAIL_AUTHENTICATION_FAILURE(75, "邮件认证失败"),
    MESSAGE_SEND_FAILURE(76, "发送消息失败"),
    MESSAGE_PARSE_FAILURE(77, "消息解析失败"),
    CONFIG_URISYNTAX(78, "出现URISyntax异常:可能配置文件不对"),
    CONFIGE_IO(79, "出现IO异常:可能配置文件找不到"),

    FAIL_AUTHORIZATION(101, "登录授权失效");

    private int code;
    private String desc;

}
