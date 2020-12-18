package pers.crayon.works.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/14 14:33
 * @since JDK 1.8
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exhibit")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Exhibit", description = "展品")
public class Exhibit extends BaseModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(name = "展品ID")
    private Long eid;

    @ApiModelProperty(name = "展品名称")
    private String name;

    @ApiModelProperty(name = "展品描述")
    private String des;

    @ApiModelProperty(name = "展品资源链接")
    private String url;
}
