package pers.crayon.works.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/7 17:06
 * @since JDK 1.8
 * <p>
 * http://localhost:10001/roam/swagger-ui/index.html
 */
@EnableOpenApi
@Configuration
public class Swagger3Config implements WebMvcConfigurer {

    @Bean(value = "simpleApi")
    public Docket simpleApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).groupName("defaultApi").select().build();
    }

    @Bean(value = "allApi")
    public Docket createAllRestApi() {
        //返回文档摘要信息
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("allApi")
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResonseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResonseMessage());
    }

    @Bean(value = "controllerApi")
    public Docket createControllerApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("controllerApi")
                .select()
                //设置扫描的包
                .apis(RequestHandlerSelectors.basePackage("pers.crayon.user.controller"))
                /*
                设置生成规则
                自定义注解设置不需要生成接口文档的方法
                 通过public ApiSelectorBuilder apis(Predicateselector)可以设置生成规则。
                 withMethodAnnotation：表示此注解是方法级别注解。 生成规则 可以自定义。
                 public static  Predicate not(Predicate predicate) :表示不允许的条件。
                 */
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //自定义注解设置不需要生成接口文档的方法
                //.apis(Predicates.not(RequestHandlerSelectors.withMethodAnnotation(NotIncludeSwagger.class)))
                //.apis(RequestHandlerSelectors.basePackage("xxxxxx"))
                /*
                设置生成接口文档的url规则  可以设置正则、ant表达式、全部、全都不
                .paths(PathSelectors.any()) regex("/test/.*")
                */
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(value = "modelApi")
    public Docket createModelApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("modelApi")
                .select()
                .apis(RequestHandlerSelectors.basePackage("pers.crayon.user.model.pojo"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(value = "authApi")
    public Docket groupApi() {
        //在配置好的配置类中增加此段代码即可
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("Authorization").description("登录校验")//name表示名称，description表示描述
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("Bearer ").build();//required表示是否必填，defaultvalue表示默认值
        pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("authApi")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))                         //这里采用包含注解的方式来确定要显示的接口
                //.apis(RequestHandlerSelectors.basePackage("com.stylefeng.guns.modular.system.controller"))    //这里采用包扫描的方式来确定要显示的接口
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);//************把消息头添加
    }

    //生成接口信息，包括标题、联系人等
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("homepage Doc")//标题
                .description("homepage API文档")//描述
                .license("协议:Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .termsOfServiceUrl("http://localhost:10001/roam")//创建人
                .contact(new Contact("漫游", "https://user.qzone.qq.com/1352456478", "910368916@qq.com"))// 联系人信息 name url email
                .version("1.0")//版本
                .build();
    }

    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("appid")
                .description("平台id")
                .required(true)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        parameters.add(new RequestParameterBuilder()
                .name("udid")
                .description("设备的唯一id")
                .required(true)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        parameters.add(new RequestParameterBuilder()
                .name("version")
                .description("客户端的版本号")
                .required(true)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    //生成通用响应信息
    private List<Response> getGlobalResonseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("找不到资源").build());
        return responseList;
    }
}
