package com.cdutcm.party.controller;

import com.cdutcm.party.dataobject.Course;
import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.service.CourseService;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/21 11:08 星期三
 * Description:
 * 经典课程
 */
@Controller
@RequestMapping("/user")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course")
    public String course(Model model) throws PartyException {
        model.addAttribute("courseList", courseService.findAll(1, 0));
        return "user/classicalPrograms";
    }

    @ResponseBody
    @GetMapping("/course/more")
    public ResultVO more(Integer type, Integer page) throws PartyException {
        return ResultVOUtils.success(courseService.findAll(type, page));
    }

    @GetMapping("/course/{courseId}")
    public String findOne(Model model, @PathVariable("courseId") Integer courseId) throws PartyException {
        model.addAttribute("course", courseService.findOne(courseId));
        return "user/courseDetail";
    }

    @ResponseBody
    @PostMapping("/course/elective")
    public ResultVO elective(Integer id, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(courseService.electiveCourse(userId, id));
    }

    @ResponseBody
    @GetMapping(value = "/course/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity download(@PathVariable("id") Integer id, HttpServletRequest request) throws IOException {
        Course course = courseService.findOne(id);
        String videoUrl = course.getVideo();
        String userAgent = request.getHeader("User-Agent");
        String courseName;
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            courseName = URLEncoder.encode(course.getName(), "UTF-8");
        } else {
            courseName = new String(course.getName().getBytes("UTF-8"), "ISO-8859-1");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment;filename=" + courseName + videoUrl.substring(videoUrl.lastIndexOf('.')));
        Resource resource = new ClassPathResource("/static" + videoUrl);
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<>(new InputStreamResource(resource.getInputStream()), headers, statusCode);
    }

    @GetMapping("/course/manage")
    public String manage(Model model,HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        model.addAttribute("courseList", courseService.findAllByUserId(userId, 0));
        return "user/courseManagement";
    }

    @ResponseBody
    @GetMapping("/course/manage/more")
    public ResultVO more(Integer page, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(courseService.findAllByUserId(userId, page));
    }

    @ResponseBody
    @PostMapping("/course/manage/delete")
    public ResultVO manageDelete(Integer id, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(courseService.eDeleteCourse(userId, id));
    }






    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam("file") MultipartFile file) {
        String name = file.getName();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
