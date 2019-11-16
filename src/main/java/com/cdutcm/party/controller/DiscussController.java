package com.cdutcm.party.controller;

import com.cdutcm.party.dataobject.Comment;
import com.cdutcm.party.dataobject.Discuss;
import com.cdutcm.party.dataobject.User;
import com.cdutcm.party.enums.CommentEnum;
import com.cdutcm.party.service.DiscussService;
import com.cdutcm.party.service.UserService;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 15:43 星期一
 * Description:
 * 讨论交流
 */
@Controller
@RequestMapping("/user")
public class DiscussController {
    @Autowired
    private DiscussService discussService;

    @GetMapping("/discuss")
    public String discuss(Model model) {
        model.addAttribute("discussList", discussService.findList(null, 0));
        return "user/discussion-list";
    }

    @ResponseBody
    @GetMapping("/discuss/more")
    public ResultVO more(Integer page) {
        return ResultVOUtils.success(discussService.findList(null, page));
    }

    @GetMapping("/discuss/{id}")
    public String findOne(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("discuss", discussService.findOne(id));
        return "user/discussionDetail";
    }

    @GetMapping("/discuss/publish")
    public String publish() {
        return "user/publish";
    }

    @ResponseBody
    @PostMapping("/discuss/save")
    public ResultVO save(HttpSession session, String content) {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(discussService.save(userId, content));
    }


    @ResponseBody
    @PostMapping("/discuss/{id}/save")
    public ResultVO saveComment(@PathVariable("id") Integer discussId,
                                String content,
                                HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(discussService.save(CommentEnum.DISCUSS, discussId, userId, content));
    }
}