package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 10:22 星期一
 * Description:
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByTypeAndObjId(Integer type, Integer objId);

    @Query("select new Comment (id, time,content) from Comment where type = ?1 and objId = ?2 order by id")
    List<Comment> findList(Integer type, Integer objId, Pageable pageable);

    Comment findByIdAndObjId(Integer id, Integer objId);

    void deleteAllByObjId(Integer discussId);
}