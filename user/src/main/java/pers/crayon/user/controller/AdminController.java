package pers.crayon.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.crayon.user.model.dto.Result;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/9/23 9:25
 * @since JDK 1.8
 */
// RestController 相当于 ResponseBody 和 Controller, 但是不能返回 html 和 Jsp 页面
// Controller 注解的方法 返回json内容 方法前要加 @ResponseBody
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @GetMapping("/set-online")
    public Result setOnline() {

        return success();
    }

    @GetMapping("list")
    public Result listAdmin() {
        return success();
    }

}
