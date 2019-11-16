package com.cdutcm.party.service;

import com.cdutcm.party.dataobject.Comment;
import com.cdutcm.party.dataobject.Discuss;
import com.cdutcm.party.dataobject.User;
import com.cdutcm.party.enums.CommentEnum;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 11:06 星期一
 * Description:
 * 讨论
 */
public interface DiscussService {
    /* 按时间倒序查询所有讨论信息列表 */
    List<Discuss> findList(User userId, Integer page);

    Discuss findOne(Integer discussId);

    List<Comment> findCommentList(CommentEnum commentEnum, Integer userId, Integer page);

    Discuss save(Integer userId, String content);

    Comment save(CommentEnum commentEnum, Integer discussId, Integer userId, String content);

    boolean deleteComment(Integer commentId, Integer userId);




    void deleteDiscuss(Integer discussId);


}
