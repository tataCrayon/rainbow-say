package pers.crayon.works.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pers.crayon.works.exception.UserOperateException;
import pers.crayon.works.model.dto.ExhibitDto;
import pers.crayon.works.model.dto.PageResult;
import pers.crayon.works.model.pojo.Exhibit;
import pers.crayon.works.repository.ExhibitionRepository;
import pers.crayon.works.service.ExhibitService;

import java.util.List;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/17 10:53
 * @since JDK 1.8
 */
@Service
public class ExhibitServiceImpl implements ExhibitService {

    @Autowired
    private ExhibitionRepository repository;

    @Override
    public void saveExhibit(Exhibit exhibit) {
        repository.save(exhibit);
    }

    @Override
    public void updateExhibit(ExhibitDto dto) {
        Long eid = dto.getEid();
        verifyExhibitByEid(eid);
        Exhibit exhibit = repository.getByEid(eid);
        exhibit.setName(dto.getName());
        exhibit.setUrl(dto.getUrl());
        exhibit.setDes(dto.getDes());
        repository.save(exhibit);

    }

    @Override
    public void delExhibit(Long eid) {
        verifyExhibitByEid(eid);
        repository.deleteByEid(eid);
    }

    @Override
    public PageResult<ExhibitDto> listExhibit(int pageNum, int pageSize) {
        List<Exhibit> list = repository.findAll();

        Sort sort = null;
        //sort = new Sort(Sort.Direction.DESC, "createTime");
        //Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        // List 转换为 pageRequest
        // 根据创建时间排序？
        //Page<Exhibit> dtoPage = new PageImpl(list, PageRequest.of(pageNum,pageSize));

        return null;
    }

    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public ExhibitDto getExhibit(String name) {
        Exhibit exhibit = repository.getByName(name);
        ExhibitDto dto = new ExhibitDto(exhibit);
        return dto;
    }

    public void verifyExhibitByEid(Long eid) {
        if (eid == null) {
            throw new UserOperateException("Exhibit no exist");
        } else {
            Exhibit exhibit = repository.getByEid(eid);
            if (exhibit == null) {
                throw new UserOperateException("Exhibit no exist");
            }
        }
    }
}
