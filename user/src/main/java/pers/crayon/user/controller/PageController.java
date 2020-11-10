package pers.crayon.user.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.crayon.user.exception.ValidateException;
import pers.crayon.user.model.dto.AdminDto;
import pers.crayon.user.model.dto.Result;
import pers.crayon.user.model.pojo.Admittance;
import pers.crayon.user.service.AdminService;
import pers.crayon.user.service.RedirectService;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/9 16:24
 * @since JDK 1.8
 * 用于登录等页面跳转
 * TODO 登录接口，使用 定向字符串 和 model? 目前不明白用那种方案实现登录页面跳转比较好
 */
// @Controller 注释返回Json内容 需要在方法添加 @ResponsetBody
@Slf4j
@Controller
@RequestMapping("/")
@Api(tags = {"页面跳转管理"})
public class PageController extends BaseController {

    @Autowired
    private RedirectService reDirectService;

    @Autowired
    private AdminService adminService;

    @RequestMapping()
    public String showLogin() {
        return "login";
    }

    //http://localhost:10001/user/login
    @PostMapping("/login")
    public String login(@RequestBody AdminDto adminDto) {
        String html = adminService.loginAdmin(adminDto);
        return html;
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.setViewName("loginR");
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        return view;
    }

    /**
     * @param admittance
     * @return
     * @RequestBody @RequestPram 用于 封装为 dto 和 msg的情况
     */
    @PostMapping("/admittance")
    public @ResponseBody
    Result validateForRedirectService(@RequestBody Admittance admittance) throws ValidateException {
        String result = null;
        result = reDirectService.validateAdmittance(admittance);
        return success(result);
    }

}
