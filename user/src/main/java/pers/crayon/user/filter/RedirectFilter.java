package pers.crayon.user.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/26 16:51
 * @since JDK 1.8
 *
 * 给请求添加请求头
 * 最常见的携带请求头的需求，
 * 无非是 referer 校验，user-agent 的防爬以及携带 cookie，
 * 使用 RestTemplate 可以借助HttpHeaders来处理请求头
 */
@Slf4j
@Component
public class RedirectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /*
    Post请求可以借助 postForObject/postForEntity 将请求头添加至HttpEntity
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String origin = request.getHeader("Origin");
        log.info("Origin:{}", origin);
        if (StringUtils.isEmpty(origin)) {
            //origin = req.getHeader("Referer");
            response.addHeader("Access-Control-Allow-Origin", "*");
        } else {
            //跨域带cookie的时候，origin必须是全匹配，不能使用*
            response.addHeader("Access-Control-Allow-Origin", origin);
        }
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Max-Age", "3600");
        // enable cookie
        response.addHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(request, response);
        //RequestWrapper request=new RequestWrapper((HttpServletRequest) servletRequest);
        //request.addHeader("header","瓜田李下");
        //
        //filterChain.doFilter(request,servletResponse);
    }

    /*
    {
        ClientHttpRequestInterceptor interceptor = (httpRequest, bytes, execution) -> {
            httpRequest.getHeaders().set("user-agent",
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
            httpRequest.getHeaders().set("cookie", "my_user_id=haha123; UN=1231923;gr_user_id=interceptor;");
            return execution.execute(httpRequest, bytes);
        };
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(interceptor);
        response = restTemplate.getForObject("http://127.0.0.1:8080/get?name=一灰灰&age=20", String.class);
        log.info("get with selfDefine header by Interceptor: {}", response);
    }
    */

    @Override
    public void destroy() {

    }

}
