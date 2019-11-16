package com.cdutcm.party.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 10:00 星期一
 * Description:
 * 讨论
 */
@Entity
@Data
public class Discuss {
    /* 讨论ID */
    @Id
    @GeneratedValue
    private Integer id;

    /* 用户名 */
    private String username;

    /* 头像 */
    private String headshot;

    /* 发布时间 */
    private Date time;

    /* 讨论内容 */
    private String content;

    /* 图片 */
    private String image;

    /* 浏览数 */
    private Integer count = 0;

    /* 评论数 */
    private Integer count2 = 0;

    /* 评论 */
    @Transient
    private List<Comment> commentList;

}
