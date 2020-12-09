package pers.crayon.user.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/3 9:39
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Opreation extends BaseModel {
    //房间号
    String roomNum;

    //科室
    String department;

    //手术开始时间

    Date startAt;

    //手术结束时间（预估结束）
    //Date endAt;

    //住院号
    String patient;

    //床号
    String bedNo;

    //术者姓名
    String username;

    //术者年龄
    int age;

    //手术诊断
    String desc;

    //手术名称
    String name;

    //麻醉方式 //半麻 全麻 手麻
    String anesthesiaType;

    //术者性别 //male 男 female 女
    String gender;

    //医生员工编号
    String[] num;

    //医院排班的唯一标示
    String originalId;


}
