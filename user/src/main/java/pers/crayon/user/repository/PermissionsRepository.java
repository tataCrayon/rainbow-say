package pers.crayon.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.crayon.user.model.pojo.Permissions;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/9 10:11
 * @since JDK 1.8
 */
public interface PermissionsRepository extends JpaRepository<Permissions, Long>,
        JpaSpecificationExecutor<Permissions> {

}
