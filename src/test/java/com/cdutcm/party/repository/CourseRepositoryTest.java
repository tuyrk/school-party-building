package com.cdutcm.party.repository;

import com.cdutcm.party.PartyApplicationTests;
import com.cdutcm.party.dataobject.Course;
import com.cdutcm.party.dataobject.News;
import com.cdutcm.party.form.CourseForm;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CourseRepositoryTest extends PartyApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findByGradeOrderByCountDesc(){
        Pageable pageable = new PageRequest(0, 10);
//        List<Course> courseList = courseRepository.findByGradeOrderByCountDesc(1, pageable);
//        print(courseList);
    }

    @Test
    public void findByGrade() {
        Sort sort = new Sort(Sort.Direction.DESC, "count");
        Pageable pageable = new PageRequest(0, 10, sort);
//        List<Course> courseList = courseRepository.findByGrade(1, pageable);
//        print(courseList);
    }

    @Test
    public void findList() {
        Sort sort = new Sort(Sort.Direction.DESC, "count");
        Pageable pageable = new PageRequest(0, 10,sort);
        List<Course> courseList = courseRepository.findList(1,pageable);
        print(courseList);
    }

    @Test
    public void findListByUser(){
        Pageable pageable = new PageRequest(0, 10);
        List<Course> objectList = courseRepository.findListByUser(1,pageable);
        for (int i = 0; i < objectList.size(); i++) {
            System.out.println(objectList.get(i));
        }
    }

    private void print(List<Course> courseList) {
        System.out.println(courseList.size());
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println("courseList = " + courseList.get(i).getId());
        }
    }
}