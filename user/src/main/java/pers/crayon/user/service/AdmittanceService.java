package pers.crayon.user.service;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/27 3:32
 * @since JDK 1.8
 * <p>
 * 用于入场问题管理
 */
public interface AdmittanceService {

    String addAdmittance(String question);

    String delAdmittance(String question);

    //设置用户在线状态
    String setOnline(String param);
}
