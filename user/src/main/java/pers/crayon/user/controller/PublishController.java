package pers.crayon.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.crayon.user.model.dto.Result;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/14 17:29
 * @since JDK 1.8
 * <p>
 * 用于发布资源：文件、图片、资源包
 * 可以选择发布或者不发布
 */
@Slf4j
@Controller
@RequestMapping("/publish")
public class PublishController extends BaseController {

    @GetMapping
    public Result delSource() {
        return null;
    }


}
