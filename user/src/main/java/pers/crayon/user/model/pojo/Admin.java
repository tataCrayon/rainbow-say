package pers.crayon.user.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/9 10:51
 * @since JDK 1.8
 */
// @Date默认仅使用该类中定义的属性且不调用父类的方法 包含 @EqualsAndHashCode(callSuper=true)
// @可通过callSuper=true解决上一点问题。让其生成的方法中调用父类的方法。
@Entity
@Table(name = "admin")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "管理员", description = "系统暂定唯一用户")
public class Admin extends BaseModel {

    @ApiModelProperty("管理员名称")
    private String name;

    @ApiModelProperty("管理员帐号密码")
    private String password;

    @ApiModelProperty("管理员电话")
    private String mobilePhone;

    @ApiModelProperty("管理员生日")
    private Date birthDate;

    @ApiModelProperty("管理员种族")
    private String nation;

    @ApiModelProperty("管理员邮箱")
    private String email;

    @ApiModelProperty(name = "是否在线", value = "说明是否在线", required = false, example = "true")
    private Boolean isOnline;

    @ApiModelProperty("状态更新时间")
    private Date onlineUpdateTime;

    @ApiModelProperty("帐号等级")
    private Integer level;

    @ApiModelProperty("管理员更新时间")
    private Date adminUpdateTime;

    @ApiModelProperty("更新的管理员ID")
    private Long updateAdminId;

    @ApiModelProperty("最后授权角色")
    private Integer lastAuthRole;

    @ApiModelProperty("最后授权时间")
    private Date lastAuthTime;
}
