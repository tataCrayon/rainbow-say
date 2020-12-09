package pers.crayon.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.crayon.user.model.pojo.Permissions;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 17:42
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
public class PermissionsDto {
    private Long id;
    private String permissionsName;

    private PermissionsDto(Permissions permissions) {
        this.id = permissions.getId();
        this.permissionsName = permissions.getPermissionsName();
    }
}
