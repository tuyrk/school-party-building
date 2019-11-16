package com.cdutcm.party.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 22:39 星期日
 * Description:
 * 热点关注
 */
@Entity
@Data
public class News {
    /* 热点关注ID */
    @Id
    @GeneratedValue
    private Integer id;

    /* 标题 */
    private String title;

    /* 头像 */
    private String headshot;

    /* 内容 */
    public String content;

    /* 发布时间 */
//    @JsonSerialize(using = DateFormatSerializer.class)
    private Date time;

    /* 来源 */
    private String source;

    /* 类型 */
    private Integer type;
}
