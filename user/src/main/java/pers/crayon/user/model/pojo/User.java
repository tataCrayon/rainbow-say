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
@Table(name = "user")
@ApiModel(value = "User", description = "用户对应实体类")
public class User {
    @Id
    @ApiModelProperty("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;

    @OneToMany
    @JoinColumn(name = "role_id")
    @ApiModelProperty(name = "用户对应的角色集合")
    private Set<Role> roles;
}
