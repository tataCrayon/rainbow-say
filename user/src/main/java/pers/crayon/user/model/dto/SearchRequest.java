package pers.crayon.user.model.dto;

import lombok.Data;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/11/23 11:11
 * @since JDK 1.8
 */
@Data
public class SearchRequest {
    private Integer form;
    private Integer pageSize;

    //设置查询条件
    private String name;
    private String age;

}
