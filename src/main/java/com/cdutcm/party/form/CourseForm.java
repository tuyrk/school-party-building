package com.cdutcm.party.form;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/21 13:37 星期三
 * Description:
 */
@Entity
@Data
public class CourseForm {
    /* 课程ID */
    @Id
    @GeneratedValue
    private Integer id;

    /* 等级 */
    private Integer grade;

    /* 封面 */
    private String cover;
    /* 名称 */
    private String name;
    /* 讲师 */
    private String teacher;
    /* 在学人数 */
    private Integer count;
}
