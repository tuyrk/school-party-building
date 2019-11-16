package com.cdutcm.party.service.impl;

import com.cdutcm.party.dataobject.Comment;
import com.cdutcm.party.dataobject.Discuss;
import com.cdutcm.party.dataobject.User;
import com.cdutcm.party.enums.CommentEnum;
import com.cdutcm.party.enums.ResultEnum;
import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.repository.CommentRepository;
import com.cdutcm.party.repository.DiscussRepository;
import com.cdutcm.party.repository.UserRepository;
import com.cdutcm.party.service.DiscussService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 13:31 星期一
 * Description:
 */
@Service
@Slf4j
public class DiscussServiceImpl implements DiscussService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiscussRepository discussRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Discuss> findList(User user, Integer page) {
        Pageable pageable = new PageRequest(page, 10);
        if (user == null) {
            return discussRepository.findAllByOrderByIdDesc(pageable);
        }
        return discussRepository.findAllByUsernameOrderByIdDesc(user.getUsername(), pageable);
    }

    @Override
    public Discuss findOne(Integer discussId) {
        Discuss discuss = discussRepository.findOne(discussId);
        if (discuss == null) {
            log.error("【交流讨论】查找讨论不存在 discussId = {}", discussId);
            throw new PartyException(ResultEnum.DISCUSS_NOT_EXIST);
        }
        discuss.setCount(discuss.getCount() + 1);
        discussRepository.save(discuss);
        discuss.setCommentList(commentRepository.findAllByTypeAndObjId(CommentEnum.DISCUSS.getCode(), discussId));
        return discuss;
    }

    @Override
    public List<Comment> findCommentList(CommentEnum commentEnum, Integer userId, Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, 10, sort);
        List<Comment> commentList = commentRepository.findList(commentEnum.getCode(), userId, pageable);
        if (commentList.size() == 0) {
            throw new PartyException(ResultEnum.NO_ANY_MORE);
        }
        System.out.println("commentList = " + commentList);
        return commentList;
    }

    @Override
    @Transactional
    public Discuss save(Integer userId, String content) {
        if (content.length() > 500) {
            log.error("【交流讨论】添加讨论失败 content = {}", content);
            throw new PartyException(ResultEnum.DISCUSS_CONTENT_OVER);
        }
        User user = userRepository.findUser(userId);
        if (user == null) {
            log.error("【交流讨论】添加讨论失败 userId = {}", userId);
            throw new PartyException(ResultEnum.USER_ERROR);
        }
        Discuss discuss = new Discuss();
        discuss.setUsername(user.getUsername());
        discuss.setHeadshot(user.getHeadshot());
        discuss.setContent(content);
        Discuss saveResult = discussRepository.save(discuss);
        if (saveResult == null) {
            log.error("【交流讨论】添加讨论失败 discuss = {}", discuss);
            throw new PartyException(ResultEnum.DISCUSS_SAVE_ERROR);
        }
        return saveResult;
    }

    @Override
    @Transactional
    public Comment save(CommentEnum commentEnum, Integer discussId, Integer userId, String content) {
        if (content.length() > 500) {
            log.error("【交流讨论】添加讨论失败 content = {}", content);
            throw new PartyException(ResultEnum.DISCUSS_CONTENT_OVER);
        }
        User user = userRepository.findUser(userId);
        if (user == null) {
            log.error("【交流讨论】添加评论失败 userId = {}", userId);
            throw new PartyException(ResultEnum.USER_ERROR);
        }
        Comment comment = new Comment();
        comment.setType(commentEnum.getCode());
        comment.setObjId(discussId);
        comment.setUsername(user.getUsername());
        comment.setHeadshot(user.getHeadshot());
        comment.setContent(content);
        Comment saveResult = commentRepository.save(comment);
        if (saveResult == null) {
            log.error("【交流讨论】添加评论失败 comment = {}", comment);
            throw new PartyException(ResultEnum.COMMENT_SAVE_ERROR);
        }
        saveResult.setTime(Calendar.getInstance().getTime());
        if (commentEnum.getCode().equals(CommentEnum.DISCUSS.getCode())) {
            Discuss discuss = discussRepository.findOne(discussId);
            discuss.setCount2(discuss.getCount2() + 1);
            discussRepository.save(discuss);
        }
        return saveResult;
    }

    @Override
    public boolean deleteComment(Integer commentId, Integer userId) {
        Comment comment = commentRepository.findByIdAndObjId(commentId, userId);
        if (comment == null) {
            log.error("【交流讨论】删除评论失败，该评论不存在 commentId = {}", commentId);
            throw new PartyException(ResultEnum.COMMENT_NOT_EXIST);
        }
        commentRepository.delete(commentId);
        return true;
    }


    @Override
    @Transactional
    public void deleteDiscuss(Integer discussId) {
        findOne(discussId);
        discussRepository.delete(discussId);
        commentRepository.deleteAllByObjId(discussId);
    }
}
