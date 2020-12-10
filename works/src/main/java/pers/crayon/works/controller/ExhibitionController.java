package pers.crayon.works.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.crayon.works.model.dto.Result;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/27 15:08
 * @since JDK 1.8
 * <p>
 * 这个接口用于在一个面板上展示想展示的内容，笔记什么的
 * 所有人可以留言，除了管理员，都有字数限制
 * emmmmm，这不是贴吧么
 */
@Slf4j
@Controller
@RequestMapping("/show")
@Api(tags = "ExhibitionController", description = "展览区接口")
public class ExhibitionController extends BaseController {

    @GetMapping
    @ApiOperation(value = "hello 测试", notes = "暂时用于测试方法是否会在swagger文档显示")
    public Result sayHello() {
        return success("hello swagger");
    }

}
