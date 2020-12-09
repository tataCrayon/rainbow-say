package pers.crayon.user.service;

import pers.crayon.user.model.dto.UserDto;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 11:43
 * @since JDK 1.8
 */
public interface LoginService {
    public UserDto getUserByName(String userName);
}
