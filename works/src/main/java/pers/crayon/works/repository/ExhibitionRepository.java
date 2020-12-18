package pers.crayon.works.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.crayon.works.model.pojo.Exhibit;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/17 10:33
 * @since JDK 1.8
 */
public interface ExhibitionRepository extends JpaRepository<Exhibit, Long>, JpaSpecificationExecutor {

    Exhibit getByEid(Long eid);

    void deleteByEid(Long eid);

    Exhibit getByName(String name);
}
