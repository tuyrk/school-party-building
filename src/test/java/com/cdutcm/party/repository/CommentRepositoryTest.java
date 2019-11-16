package com.cdutcm.party.repository;

import com.cdutcm.party.PartyApplicationTests;
import com.cdutcm.party.dataobject.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CommentRepositoryTest extends PartyApplicationTests {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void save() {
        for (int i = 0; i < 40; i++) {
            Comment comment = new Comment();
            comment.setObjId(i % 9 + 1);
            comment.setUsername(String.valueOf((i + 1)));
            comment.setHeadshot(String.valueOf((i + 1)));
            comment.setContent(String.valueOf((i + 1)));
            comment.setImage(String.valueOf((i + 1)));
            commentRepository.save(comment);
        }
    }

    @Test
    public void findByDiscussId() {
        List<Comment> commentList = commentRepository.findAllByTypeAndObjId(1,1);
        for (Comment comment : commentList) {
            System.out.println("comment = " + comment);
        }
    }
}