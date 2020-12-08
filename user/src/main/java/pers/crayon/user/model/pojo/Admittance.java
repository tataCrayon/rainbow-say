package pers.crayon.user.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/19 16:16
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "admittance")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Admittance验证问题", description = "用于一些场景的验证")
public class Admittance extends BaseModel implements Serializable {

    @ApiModelProperty(name = "序列化code")
    private static final Long serialVersionID = 79846516446513L;
    @ApiModelProperty("验证问题")
    private String question;
    @ApiModelProperty("验证问题的答案")
    private String answer;
    @ApiModelProperty("问题创建者")
    private Long createAdminId;


}
