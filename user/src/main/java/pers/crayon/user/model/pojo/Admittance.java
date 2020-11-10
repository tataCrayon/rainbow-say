package pers.crayon.user.model.pojo;

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
public class Admittance extends BaseModel implements Serializable {

    private static final Long serialVersionID = 79846516446513L;

    private String question;
    private String answer;
    private Long createAdminId;


}
