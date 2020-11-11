package pers.crayon.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import pers.crayon.user.constant.enums.ResponseCodeEnum;
import pers.crayon.user.model.dto.PageResult;
import pers.crayon.user.model.dto.Result;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author tataCrayon
 * @version 1.0
 * @Scope默认单例模式，可用于调整作用域 </p>
 * @date 2020/9/23 11:45
 * @since JDK 1.8
 * <p>
 *     使用JSON格式返回方法
 * </p>
 */
@Slf4j
@Scope
public class BaseController {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    protected Result success() {
        return new Result(ResponseCodeEnum.OK);
    }

    protected Result success(Object object) {
        return new Result(ResponseCodeEnum.OK, object);
    }

    protected Result<T> success(T data) {
        return new Result(ResponseCodeEnum.OK, data);
    }

    protected Result success(Page page) {
        return new Result(ResponseCodeEnum.OK, new PageResult<Page>(page));
    }

    protected Result success(PageResult pageResult) {
        if (pageResult.getItems() == null) {
            pageResult.setItems(new ArrayList());
        }
        return new Result(ResponseCodeEnum.OK, pageResult);
    }
}
