package com.cdutcm.party.controller;

import com.cdutcm.party.dataobject.User;
import com.cdutcm.party.enums.CommentEnum;
import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.repository.UserRepository;
import com.cdutcm.party.service.DiscussService;
import com.cdutcm.party.service.UserService;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/22 21:22 星期四
 * Description:
 */
@Controller
@RequestMapping("/user")
public class PersonalController {

    @Autowired
    private DiscussService discussService;
    @Autowired
    private UserService userService;

    @GetMapping("/personal")
    public String self(Model model, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("discussList", discussService.findList(user, 0));
        return "user/personalCenter";

    }

    @ResponseBody
    @GetMapping("/personal/discuss/more")
    public ResultVO discuss(Integer page, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.findUser(userId);
        return ResultVOUtils.success(discussService.findList(user, page));
    }

    @ResponseBody
    @GetMapping("/personal/impress")
    public ResultVO impress(Integer page, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(discussService.findCommentList(CommentEnum.STUDY, userId, page));
    }

    @ResponseBody
    @PostMapping("/personal/impress/save")
    public ResultVO saveImpress(String content, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(discussService.save(CommentEnum.STUDY, userId, userId, content));
    }

    @ResponseBody
    @GetMapping("/personal/goal")
    public ResultVO goal(Integer page, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(discussService.findCommentList(CommentEnum.GOAL, userId, page));
    }

    @ResponseBody
    @PostMapping("/personal/goal/save")
    public ResultVO saveGoal(String content, HttpSession session) throws PartyException {
        System.out.println("content = " + content);
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(discussService.save(CommentEnum.GOAL, userId, userId, content));
    }

    @ResponseBody
    @PostMapping("/personal/delete")
    public ResultVO delete(Integer id, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(discussService.deleteComment(id, userId));
    }
}
