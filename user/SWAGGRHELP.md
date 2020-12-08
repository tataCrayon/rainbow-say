* swagger 2访问链接: http://localhost:10001/roam/swagger-ui.html
* swagger 3访问链接: http://localhost:10001/roam/swagger-ui/index.html
*
*
* @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Integer")
* 可以配置具体属性
* @Api：修饰整个类，描述Controller的作用
* @ApiOperation：描述一个类的一个方法，或者说一个接口
* @ApiParam：单个参数描述
*      name：参数名称
*      value：参数描述
*      required：是否是必须
* @ApiModel：用对象来接收参数
* @ApiProperty：用对象接收参数时，描述对象的一个字段
*      value：描述
*      name：重写属性名
*      required：是否是必须的
*      example：示例内容
*      hidden：是否隐藏。
* @ApiResponse：HTTP响应其中1个描述
* @ApiResponses：HTTP响应整体描述
* @ApiIgnore：使用该注解忽略这个API
* @ApiError：发生错误返回的信息
* @ApiImplicitParam：一个请求参数
* @ApiImplicitParams：多个请求参数
* @since JDK 1.8
* 应用的时候 在类上配置@Api(tags = {"登录日志管理"})
* 方法上配置@ApiOperation(value = "条件查询")