package com.cdutcm.party.dto;

import com.cdutcm.party.dataobject.Comment;
import com.cdutcm.party.utils.serializer.DateFormatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 11:04 星期一
 * Description:
 */
@Data
public class DiscussDTO {
    /* 讨论ID */
    private Integer id;

    /* 用户名 */
    private String username;

    /* 头像 */
    private String headshot;

    /* 发布时间 */
    @JsonSerialize(using = DateFormatSerializer.class)
    private Date time;

    /* 讨论内容 */
    private String content;

    /* 图片 */
    private String image;

    /* 评论数 */
    private Integer count;

    private List<Comment> commentList;
}
