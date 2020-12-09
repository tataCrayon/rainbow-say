package pers.crayon.user.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/22 11:29
 * @since JDK 1.8
 * <p>
 * 处理 404 不存在路径
 * </p>
 */
@Controller
public class ErrorPageController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(ERROR_PATH)
    public String error() {
        return "404";
    }
}
