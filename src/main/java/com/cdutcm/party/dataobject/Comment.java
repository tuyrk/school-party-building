package com.cdutcm.party.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 10:02 星期一
 * Description:
 * 评论
 */
@Entity
@Data
public class Comment {
    /* 讨论ID */
    @Id
    @GeneratedValue
    private Integer id;

    /* 评论类型 */
    private Integer type;

    /* 评论对象ID */
    private Integer objId;

    /* 评论姓名 */
    private String username;

    /* 评论头像 */
    private String headshot;

    /* 评论发布时间 */
    private Date time;

    /* 评论内容 */
    private String content;

    /* 评论图片 */
    private String image;

    public Comment() {
    }

    public Comment(Integer id, Date time, String content) {
        this.id = id;
        this.time = time;
        this.content = content;
    }
}
