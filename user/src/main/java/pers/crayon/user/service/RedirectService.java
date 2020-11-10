package pers.crayon.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.crayon.user.exception.ValidateException;
import pers.crayon.user.model.pojo.Admittance;
import pers.crayon.user.repository.AdmittanceRepository;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/19 16:08
 * @since JDK 1.8
 *
 * <p></p>
 */
@Slf4j
@Service
public class RedirectService {

    @Autowired
    private AdmittanceRepository admittanceRepository;

    /**
     * @param admittance
     * @return
     *
     * TODO 非空校验出现了 空指针异常
     */
    public String validateAdmittance(Admittance admittance) {
        if (admittance == null) {
            throw new ValidateException("验证问题不能为空");
        }
        String question = admittance.getQuestion();
        String answer = admittance.getAnswer();
        if (null == question || null == answer) {
            throw new ValidateException("验证问题不能为空");
        }
        String trueAnswer = admittanceRepository.getByQuestion(question).getAnswer();
        if (!answer.trim().equals(trueAnswer)) {
            throw new ValidateException("验证答案有误");
        }
        return "验证通过";
    }

    public String validateAdmittance(Admittance admittance, Exception ex) {

        if (null == admittance) {
            throw new ValidateException("验证问题不能为空");
        }
        String question = admittance.getQuestion();
        String answer = admittance.getAnswer();
        if (null == question || null == answer) {
            throw new ValidateException("验证问题不能为空");
        }
        String trueAnswer = admittanceRepository.getByQuestion(question).getAnswer();
        if (!answer.trim().equals(trueAnswer)) {
            throw new ValidateException("验证答案有误");
        }
        if (ex instanceof NullPointerException) {
            NullPointerException nullPointerException = (NullPointerException) ex;
            String errorMsg = "Cause:" + nullPointerException.getCause() + ",Message:" + nullPointerException.getMessage();
            log.error(errorMsg);
        }
        return "验证通过";
    }

}
