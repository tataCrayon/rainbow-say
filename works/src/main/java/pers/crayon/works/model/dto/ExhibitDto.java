package pers.crayon.works.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.crayon.works.model.pojo.Exhibit;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/17 10:39
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitDto {

    @ApiModelProperty(name = "展品ID")
    private Long eid;

    @ApiModelProperty(name = "展品名称")
    private String name;

    @ApiModelProperty(name = "展品描述")
    private String des;

    @ApiModelProperty(name = "展品资源链接")
    private String url;

    public ExhibitDto(Exhibit exhibit) {
        this.name = exhibit.getName();
        this.des = exhibit.getDes();
        this.url = exhibit.getUrl();
    }
}
