package pers.crayon.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.crayon.user.enums.ResponseCodeEnum;
import pers.crayon.user.model.dto.Result;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/19 16:59
 * @since JDK 1.8
 * <p>
 * 都含有 @ExceptionHandler 注释可以用于处理异常，@Rest 自动加油 @ResponseBody
 * <p>
 * ex是业务层抛出的自定义异常则或取自定义异常的自定义状态码和自定义消息+错误堆栈信息；
 * 如果ex不是自定义异常，则获取ex的错误堆栈信息
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidateException.class)
    public Result ValidateExceptionHandler(ValidateException ex) {
        log.error("ValidationException:{}", ex);
        return new Result(ResponseCodeEnum.USERNAME_OR_PWD_ERROR, ex.getMessage());
    }

    @ExceptionHandler(UserOperateException.class)
    public Result UserOperateExceptionHandler(UserOperateException ex) {
        log.error("UserOperateException:{}", ex);
        return new Result(ResponseCodeEnum.USER_OPERATE_ERROR, ex.getMessage());
    }
}
