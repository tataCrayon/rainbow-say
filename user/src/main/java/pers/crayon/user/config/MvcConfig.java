package pers.crayon.user.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import pers.crayon.user.filter.RedirectFilter;

import javax.annotation.Resource;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/9/16 11:17
 *
 * MVC 视图配置
 * WebMvcConfigurerAdapter 已经过时
 *
 * WebMvcConfigurationSupport 安全扩展 WebMvcConfigurer
 */
//@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    // 自定义URL对应的静态资源
    //@Override
    //public void addViewControllers(ViewControllerRegistry registry) {
    //    registry.addViewController("/index").setViewName("index.jsp");
    //    registry.addViewController("/").setViewName("index.jsp");
    //    registry.addViewController("/hello").setViewName("hello");
    //    registry.addViewController("/login").setViewName("login");
    //}

    /*
    自定义资源目录配置
    可以在配置文件添加如下配置达到一样效果
    spring.mvc.static-path-pattern=/static/**
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    //-----------------注册拦截器，对指定请求添加header-----------------
    @Resource
    private RedirectFilter redirectFilter;

    @Bean
    public FilterRegistrationBean<RedirectFilter> initFilterRegistrationBean() {
        FilterRegistrationBean<RedirectFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(redirectFilter);
        registrationBean.addUrlPatterns("/hello");
        registrationBean.setOrder(0);

        return registrationBean;
    }
}