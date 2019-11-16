package com.cdutcm.party.service;

import com.cdutcm.party.dataobject.Course;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/21 11:10 星期三
 * Description:
 */
public interface CourseService {
    /* 按学习人数查询课程 */
    List<Course> findAll(Integer type, Integer page);

    List<Course> findAllByUserId(Integer userId, Integer page);

    Course findOne(Integer courseId);

    boolean electiveCourse(Integer userId,Integer courseId);

    boolean eDeleteCourse(Integer userId,Integer courseId);
}
