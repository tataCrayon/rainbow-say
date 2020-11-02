package pers.crayon.user.exception;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/19 17:08
 * @since JDK 1.8
 */
public class UserOperateException extends RuntimeException {
    public UserOperateException(String msg) {
        super(msg);
    }

    public UserOperateException(Throwable t) {
        super(t);
    }
}
