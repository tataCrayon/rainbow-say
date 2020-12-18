package pers.crayon.works.service;

import pers.crayon.works.model.dto.ExhibitDto;
import pers.crayon.works.model.dto.PageResult;
import pers.crayon.works.model.pojo.Exhibit;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/17 10:45
 * @since JDK 1.8
 */
public interface ExhibitService {

    void saveExhibit(Exhibit dto);

    void updateExhibit(ExhibitDto dto);

    void delExhibit(Long eid);

    PageResult listExhibit(int pageNum, int pageSize);

    ExhibitDto getExhibit(String name);
}
