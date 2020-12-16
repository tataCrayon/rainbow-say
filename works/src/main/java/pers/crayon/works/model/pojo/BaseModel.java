package pers.crayon.works.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/9 15:07
 * @since JDK 1.8
 */
// @MappedSuperclass 将实体类 的 不同属性 封装到 不同类 中
// @EntityListeners(AuditingEntityListener.class) 在JPA中使用， 对实体属性的变化进行跟踪
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@ApiModel(value = "BaseModel基础字段", description = "所有model都有的基础字段")
public class BaseModel {
    @Id
    @ApiModelProperty("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @ApiModelProperty(value = "创建时间", example = "yyyy/MM/dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    @JsonIgnore
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间", example = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;
}
