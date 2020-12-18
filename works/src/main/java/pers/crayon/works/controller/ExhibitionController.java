package pers.crayon.works.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.crayon.works.model.dto.ExhibitDto;
import pers.crayon.works.model.dto.Result;
import pers.crayon.works.model.pojo.Exhibit;
import pers.crayon.works.service.ExhibitService;

import static pers.crayon.works.constant.Constant.DEFAULT_PAGE_NUM;
import static pers.crayon.works.constant.Constant.DEFAULT_PAGE_SIZE;

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
@RestController
@RequestMapping("/show")
@Api(tags = "ExhibitionController", description = "展览区接口")
public class ExhibitionController extends BaseController {
    //@RequestParam @PathVariable @RequestBody 在设置里面有 ID 使用 @PathVariable

    @Autowired
    private ExhibitService service;

    @PostMapping("/add")
    public Result addExhibit(@RequestBody ExhibitDto dto) {
        Exhibit exhibit = new Exhibit();
        exhibit.setName(dto.getName());
        exhibit.setUrl(dto.getUrl());
        exhibit.setDes(dto.getDes());
        service.saveExhibit(exhibit);
        return success();
    }

    @PostMapping("/update")
    public Result updateExhibit(@RequestBody ExhibitDto dto) {
        service.updateExhibit(dto);
        return success();
    }

    @GetMapping("/list")
    public Result listExhibit(@RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUM) int pageNum,
                              @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return success(service.listExhibit(pageNum, pageSize));
    }

    @GetMapping("/del/{eid}")
    public Result delExhibit(@PathVariable("eid") Long eid) {
        service.delExhibit(eid);
        return success();
    }

    @GetMapping("/search")
    public Result searchExhibit(@RequestParam String name) {
        service.getExhibit(name);
        return success();
    }
}