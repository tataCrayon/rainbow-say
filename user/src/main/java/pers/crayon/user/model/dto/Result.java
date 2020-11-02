package pers.crayon.user.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pers.crayon.user.enums.ResponseCodeEnum;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/9/23 11:52
 * @since JDK 1.8
 */
@Getter
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    // 可以传枚举类，把 msg 存储在对应的 code

    public Result(ResponseCodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getDesc();
    }

    public Result(ResponseCodeEnum code, String msg) {
        this.code = code.getCode();
        this.msg = msg;
    }

    public Result(ResponseCodeEnum code, T data) {
        this.code = code.getCode();
        this.msg = code.getDesc();
        this.data = data;
    }


}
