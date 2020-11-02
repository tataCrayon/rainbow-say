package pers.crayon.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.crayon.user.model.pojo.Admin;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/9 10:48
 * @since JDK 1.8
 *
 * 继承JPA系列接口实现CURD ，继承JpaSpecificationExecutor 实现复杂查询
 */

public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

}
