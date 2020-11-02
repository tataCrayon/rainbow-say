package pers.crayon.user.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/26 17:49
 * @since JDK 1.8
 */
@Slf4j
@Component
public class ContentFilter implements Filter {

    private String words[];
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        words = new String[]{"可恶"};
        encoding = filterConfig.getInitParameter("encoding");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (encoding != null) {
            servletRequest.setCharacterEncoding(encoding);//设置request字符编码
            servletRequest = new Request((HttpServletRequest) servletRequest);
            //将传递的ServletRequest对象转化为自定义的Request对象，即可实现非法字符的过滤
            servletResponse.setContentType("text/html;charset=" + encoding);//设置response字符编码
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 销毁创建的实例和资源
     */
    @Override
    public void destroy() {
        this.words = null;
        this.encoding = null;
    }


    class Request extends HttpServletRequestWrapper {
	/*创建内部类Request，
	该类继承HttpServletRequestWrapper，
	是HttpServletRequest的装饰类，
	用来改变HttpServletRequest的状态，
	从而达到对请求内容的过滤的功能*/

        public Request(HttpServletRequest request) {
            super(request);
            // TODO 自动生成的构造函数存根
        }

        /*重写getParameter方法
         * 对请求结果进行过滤*/
        public String getParameter(String name) {
            return filter(super.getRequest().getParameter("name"));
        }

        /*重写getParameterValues方法
         * 通过循环取出每一个请求结果
         * 再对请求结果进行过滤*/
        public String[] getParameterValues(String name) {
            String values[] = super.getRequest().getParameterValues("name");
            for (int i = 0; i < values.length; i++) {
                values[i] = filter(values[i]);
            }
            return values;
        }
    }

    /*创建过滤方法filter
     * 当敏感字不为空的时候，
     * 分别对每一个敏感字循环一次
     * 如果在param中发现敏感字则将其替换为“****” */
    public String filter(String param) {
        try {
            if (words != null && words.length > 0) {
                for (int i = 0; i < words.length; i++) {
                    if (param.indexOf(words[i]) != -1) {
                        param = param.replaceAll(words[i], "****");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }
}
