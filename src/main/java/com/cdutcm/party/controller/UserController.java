package com.cdutcm.party.controller;

import com.cdutcm.party.dataobject.User;
import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.enums.ResultEnum;
import com.cdutcm.party.service.UserService;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 17:14 星期日
 * Description:
 * 个人中心
 */
@Controller
@Slf4j
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/info")
    public String info(Model model, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        model.addAttribute("user", userService.findOne(userId));
        return "user/personaInfoEdit";
    }

    @PostMapping("/user/save")
    public String save(User user, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        if (!user.getId().equals(userId)) {
            throw new PartyException(ResultEnum.USER_ERROR);
        }
        userService.save(user,userId);
        return "redirect:/user/info";
    }

    @GetMapping("/user/headshot")
    public String headshot(Model model, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        model.addAttribute("user", userService.findUser(userId));
        return "user/headPortraits";
    }

    @PostMapping("/user/headshot/save")
    public String save(MultipartFile file, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        userService.saveHeadshot(file, userId);
        return "redirect:/user/headshot";
    }

    @GetMapping("/user/modify")
    public String modify() {
        return "user/modifyPassword";
    }

    @ResponseBody
    @PostMapping("/user/modify/save")
    public ResultVO modify(String oldPassword, String newPassword, HttpSession session) throws PartyException {
        Integer userId = (Integer) session.getAttribute("userId");
        return ResultVOUtils.success(userService.modify(userId, oldPassword, newPassword));
    }


    @GetMapping("/")
    public String login(HttpSession session) {
        session.invalidate();
        return "user/login";
    }

    @ResponseBody
    @PostMapping("/sendCode")
    public ResultVO sendCode(String mail, HttpSession session) throws PartyException {
        String code = userService.sendCode(mail.trim());
        if (code != null) {
            session.setAttribute("mail", mail.trim());
            return ResultVOUtils.success(code);//发送成功
        }
        return ResultVOUtils.success("fail");//发送失败
    }

    @ResponseBody
    @PostMapping("/register")
    public ResultVO register(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【用户注册】用户信息错误 userForm = {}", user);
            throw new PartyException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        userService.add(user);

        return ResultVOUtils.success("success");
    }

    @GetMapping("/retrieve")
    public String retrieve() {
        return "user/forgetPassword";
    }

    @ResponseBody
    @PostMapping("/retrieve/save")
    public ResultVO retrieve(String mail, String password, HttpSession session) {
        String mailSession = (String) session.getAttribute("mail");
        session.invalidate();
        userService.retrieve(mail,mailSession,password);
        return ResultVOUtils.success();
    }

    @ResponseBody
    @PostMapping("/login")
    public ResultVO login(String username, String password, HttpSession session) {
        User user = userService.findOne(username, password);
        session.setAttribute("userId", user.getId());
        return ResultVOUtils.success("success");
    }
}
