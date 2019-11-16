package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 23:27 星期一
 * Description:
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {


//    List<Course> findByGradeOrderByCountDesc(Integer type, Pageable pageable);

//    List<Course> findByGrade(Integer type, Pageable pageable);

    @Query("select new Course (c.id,c.grade,c.cover,c.name,c.teacher,c.count) from Course c where c.grade = ?1")
    List<Course> findList(Integer grade, Pageable pageable);

    @Query(value = "SELECT new Course (c.id,c.grade,c.cover,c.name,c.teacher,c.count) FROM Course c , Elective e WHERE c.id = e.courseId and e.userId = ?1 ORDER BY e.time")
    List<Course> findListByUser(Integer userId, Pageable pageable);
}
