package pers.crayon.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/10 17:16
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 总条数
     */
    private long totalSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数据
     */
    private List<T> items;


    public PageResult(Page<T> p) {
        this.totalSize = p.getTotalElements();
        this.totalPage = p.getTotalPages();
        this.items = p.getContent();
    }
}
