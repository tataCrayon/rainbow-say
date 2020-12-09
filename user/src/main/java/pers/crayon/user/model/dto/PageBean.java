package pers.crayon.user.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/11/23 9:51
 * @since JDK 1.8
 *
 * 忘记是干什么的了，功用和 PageResult 重复了
 */
@Data
public class PageBean<T> {
    // 当前页数、从页面获取？
    private int currentPage;

    // 每页数据个数
    private int currentCount;

    // 总条数
    private int totalRecord;

    // 总页数
    private int totalPage;
    //java.awt.List
    List<T> list = new ArrayList<>();
}
