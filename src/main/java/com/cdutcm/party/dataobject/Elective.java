package com.cdutcm.party.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/21 23:31 星期三
 * Description:
 * 选课表
 */
@Entity
@Data
public class Elective implements Serializable {

    /* ID */
    @Id
    @GeneratedValue
    private Integer id;
    /* 用户ID */
    private Integer userId;
    /* 课程ID */
    private Integer courseId;
    /* 添加时间 */
    private Date time;

    public Elective() {
    }

    public Elective(Integer userId, Integer courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }
}
