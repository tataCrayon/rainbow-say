package pers.crayon.user.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 11:39
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "permissions")
@ApiModel(value = "Permissions", description = "权限对应实体类")
public class Permissions {

    @Id
    @ApiModelProperty("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionsName;
}
