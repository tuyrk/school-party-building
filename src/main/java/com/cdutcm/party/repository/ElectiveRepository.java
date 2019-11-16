package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.Elective;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/21 23:40 星期三
 * Description:
 */
public interface ElectiveRepository extends JpaRepository<Elective, Integer> {
    Elective findByUserIdAndCourseId(Integer userId,Integer courseId);

//    void deleteByUserIdAndCourseId(Integer userId,Integer courseId);
}
