package pers.crayon.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

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
}
