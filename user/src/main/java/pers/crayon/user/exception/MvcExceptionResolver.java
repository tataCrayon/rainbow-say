package pers.crayon.user.exception;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import pers.crayon.user.utils.AjaxUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/22 10:25
 * @component （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
 * @Slf4j 代替当前类的日志对象
 * @since JDK 1.8
 * <p>
 * MVC异常处理器
 * 用于500错误页面视图映射
 */
@Slf4j
@Component
public class MvcExceptionResolver implements HandlerExceptionResolver {

    // 可用 @Slf4j 代替
    //private final Logger logger = LoggerFactory.getLogger(MvcExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String errorMsg = "";
            boolean isAjax = "1".equals(request.getParameter("isAjax"));

            //ex 为业务层抛出的自定义异常
            if (ex instanceof ValidateException) {
                ValidateException validateEx = (ValidateException) ex;

                errorMsg = "customStatus:" + validateEx.getValidateStatus() + ",customMessage:" + validateEx.getValidateMsg()
                        + "\r\n" + ExceptionUtils.getStackTrace(validateEx);
                log.error(errorMsg);
            } else {
                //ex为非自定义异常，则
                errorMsg = ExceptionUtils.getStackTrace(ex);
                log.error(errorMsg);
            }
            if (isAjax) {

                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(AjaxUtil.messageMap(500, errorMsg)));
                return new ModelAndView();
            } else {
                //否则，  输出错误信息到自定义的500.jsp页面
                ModelAndView mv = new ModelAndView("/error/500.jsp");
                mv.addObject("errorMsg", errorMsg);
                return mv;
            }
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return new ModelAndView();
    }


}
