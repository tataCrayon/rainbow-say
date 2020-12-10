package pers.crayon.works.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/19 17:17
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ValidateException extends RuntimeException {
    private Integer validateStatus;

    private String validateMsg;

    private static final long serialVersionUID = 1L;

    public ValidateException() {
        super();
    }

    public ValidateException(String ValidateMsg) {
        super(ValidateMsg);
    }

    public ValidateException(Integer validateStatus, String validateMsg) {
        this.validateStatus = validateStatus;
        this.validateMsg = validateMsg;
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    public ValidateException(Integer validateStatus, String validateMsg, Throwable cause) {
        super(cause);
        this.validateStatus = validateStatus;
        this.validateMsg = validateMsg;
    }

}
