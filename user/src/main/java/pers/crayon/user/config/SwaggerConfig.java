package pers.crayon.user.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/26 14:46
 * @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Integer")
 * 可以配置具体属性
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiParam：单个参数描述
 * @ApiModel：用对象来接收参数
 * @ApiProperty：用对象接收参数时，描述对象的一个字段
 * @ApiResponse：HTTP响应其中1个描述
 * @ApiResponses：HTTP响应整体描述
 * @ApiIgnore：使用该注解忽略这个API
 * @ApiError：发生错误返回的信息
 * @ApiImplicitParam：一个请求参数
 * @ApiImplicitParams：多个请求参数
 * @since JDK 1.8
 * <p>
 * 应用的时候 在类上配置@Api(tags = {"登录日志管理"})
 * 方法上配置@ApiOperation(value = "条件查询")
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //.apis(RequestHandlerSelectors.basePackage("xxxxxx"))
                .paths(PathSelectors.any())
                .build();

        ////在配置好的配置类中增加此段代码即可
        //ParameterBuilder ticketPar = new ParameterBuilder();
        //List<Parameter> pars = new ArrayList<Parameter>();
        //ticketPar.name("Authorization").description("登录校验")//name表示名称，description表示描述
        //        .modelRef(new ModelRef("string")).parameterType("header")
        //        .required(false).defaultValue("Bearer ").build();//required表示是否必填，defaultvalue表示默认值
        //pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效
        //
        //return new Docket(DocumentationType.SWAGGER_2)
        //        .apiInfo(apiInfo())
        //        .select()
        //        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))                         //这里采用包含注解的方式来确定要显示的接口
        //        //.apis(RequestHandlerSelectors.basePackage("com.stylefeng.guns.modular.system.controller"))    //这里采用包扫描的方式来确定要显示的接口
        //        .paths(PathSelectors.any())
        //        .build()
        //        .globalOperationParameters(pars);//************把消息头添加

    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("homepage Doc")//标题
                .description("homepage API文档")//描述
                .termsOfServiceUrl("roam")//创建人
                .version("1.0")//版本
                .build();
    }

}
