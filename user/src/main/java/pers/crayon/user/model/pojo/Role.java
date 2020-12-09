package pers.crayon.user.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 11:38
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "role")
@ApiModel(value = "Role", description = "角色对应实体类")
public class Role {

    @Id
    @ApiModelProperty("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @OneToMany
    @JoinColumn(name = "permissions_id")
    @ApiModelProperty(name = "角色对应权限集合")
    private Set<Permissions> permissions;
}
