package pers.crayon.user.exception;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 15:16
 * @since JDK 1.8
 */
public class LoginException extends RuntimeException {

    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(Throwable t) {
        super(t);
    }
}
