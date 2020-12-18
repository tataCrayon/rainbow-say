package pers.crayon.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.crayon.user.model.dto.Result;
import pers.crayon.user.service.AdmittanceService;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/9/23 9:25
 * @since JDK 1.8
 */
/*
 RestController 相当于 ResponseBody 和 Controller, 但是不能返回 html 和 Jsp 页面
 Controller 注解的方法 返回json内容 方法前要加 @ResponseBody
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "AdminController", description = "管理员入口层")
public class AdminController extends BaseController {

    @Autowired
    private AdmittanceService admittanceService;

    @GetMapping("/set-online")
    @ApiOperation(value = "设置在线", notes = "设置在线情况")
    public Result setOnline() {
        return success();
    }

    @GetMapping("list")
    @ApiOperation(value = "管理员列表", notes = "查询管理员列表")
    public Result listAdmin() {
        return success();
    }

}
