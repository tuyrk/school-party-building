package com.cdutcm.party.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 23:24 星期一
 * Description:
 * 课程
 */
@Entity
@Data
public class Course {
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

    /* 视频 */
    private String video;
    /* 章节 */
    private String chapter;
    /* 详情 */
    private String details;
    /* 评论 */
    @Transient
    private List<Comment> commentList;

    public Course() {
    }

    public Course(Integer id, Integer grade, String cover, String name, String teacher, Integer count) {
        this.id = id;
        this.grade = grade;
        this.cover = cover;
        this.name = name;
        this.teacher = teacher;
        this.count = count;
    }
}