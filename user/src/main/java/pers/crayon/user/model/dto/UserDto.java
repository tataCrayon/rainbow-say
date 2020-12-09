package pers.crayon.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.crayon.user.model.pojo.User;

import java.util.Set;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 17:42
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String password;

    private Set<RoleDto> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
    }
}
