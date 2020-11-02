package pers.crayon.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.crayon.user.model.pojo.Admittance;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/19 16:16
 * @since JDK 1.8
 */
public interface AdmittanceRepository extends JpaRepository<Admittance, Long>, JpaSpecificationExecutor<Admittance> {

    //Admittance findByQuestion();
    Admittance getByQuestion(String question);
}
