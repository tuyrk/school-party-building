package com.cdutcm.party.service.impl;

import com.cdutcm.party.dataobject.Course;
import com.cdutcm.party.dataobject.Elective;
import com.cdutcm.party.enums.CommentEnum;
import com.cdutcm.party.enums.ResultEnum;
import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.repository.CommentRepository;
import com.cdutcm.party.repository.CourseRepository;
import com.cdutcm.party.repository.ElectiveRepository;
import com.cdutcm.party.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/21 11:10 星期三
 * Description:
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ElectiveRepository electiveRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Course> findAll(Integer type, Integer page) {
        if (0 > type || type > 3) {
            log.error("【经典课程】类目编号不正确（1,2,3）type = {}", type);
            throw new PartyException(ResultEnum.COURSE_TYPE_ERROR);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "count");
        Pageable pageable = new PageRequest(page, 10, sort);
        List<Course> courseList = courseRepository.findList(type, pageable);
        if (courseList.size() == 0) {
//            throw new PartyException(ResultEnum.NO_ANY_MORE);
            return null;
        }
        return courseList;
    }

    @Override
    public List<Course> findAllByUserId(Integer userId, Integer page) {
        Pageable pageable = new PageRequest(page, 10);
//        List<Course> courseList = courseRepository.findListByUser(userId, pageable);
//        if (courseList.size() == 0) {
//            throw new PartyException(ResultEnum.NO_ANY_MORE);
//        }
        return courseRepository.findListByUser(userId, pageable);
    }

    @Override
    public Course findOne(Integer courseId) {
        Course course = courseRepository.findOne(courseId);
        if (course == null) {
            log.error("【经典课程】该课程不存在 course = {}", courseId);
            throw new PartyException(ResultEnum.COURSE_NOT_EXIST);
        }
        course.setCommentList(commentRepository.findAllByTypeAndObjId(CommentEnum.COURSE.getCode(), course.getId()));
        return course;
    }

    @Override
    @Transactional
    public boolean electiveCourse(Integer userId, Integer courseId) {
        Course course = findOne(courseId);
        if (electiveRepository.findByUserIdAndCourseId(userId, courseId) != null) {
            throw new PartyException(ResultEnum.ELECTIVE_IS_EXIST);
        }
        course.setCount(course.getCount() + 1);
        courseRepository.save(course);
        electiveRepository.save(new Elective(userId, courseId));
        return true;
    }

    @Override
    @Transactional
    public boolean eDeleteCourse(Integer userId, Integer courseId) {
        Course course = findOne(courseId);
        Elective elective = electiveRepository.findByUserIdAndCourseId(userId, courseId);
        if (elective == null) {
            throw new PartyException(ResultEnum.ELECTIVE_NOT_EXIST);
        }
        course.setCount(course.getCount() - 1);
        courseRepository.save(course);
        electiveRepository.delete(elective);
        return true;
    }
}
