package pers.crayon.user.service.serviceimpl;

import org.springframework.stereotype.Service;
import pers.crayon.user.model.dto.PermissionsDto;
import pers.crayon.user.model.dto.RoleDto;
import pers.crayon.user.model.dto.UserDto;
import pers.crayon.user.service.LoginService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 11:40
 * @since JDK 1.8
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public UserDto getUserByName(String userName) {
        return getMapByName(userName);
    }

    /**
     * 模拟数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    private UserDto getMapByName(String userName) {
        PermissionsDto permissions1 = new PermissionsDto(1L, "query");
        PermissionsDto permissions2 = new PermissionsDto(2L, "add");
        Set<PermissionsDto> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        RoleDto role = new RoleDto(1L, "admin", permissionsSet);
        Set<RoleDto> roleSet = new HashSet<>();
        roleSet.add(role);
        UserDto user = new UserDto(1L, "wsl", "123456", roleSet);

        Map<String, UserDto> map = new HashMap<>();
        map.put(user.getUserName(), user);

        Set<PermissionsDto> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions1);
        RoleDto role1 = new RoleDto(2L, "user", permissionsSet1);
        Set<RoleDto> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        UserDto user1 = new UserDto(2L, "zhangsan", "123456", roleSet1);
        map.put(user1.getUserName(), user1);
        return map.get(userName);
    }
}