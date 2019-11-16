package com.cdutcm.party.controller;

import com.cdutcm.party.dataobject.Social;
import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.service.SocialService;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 19:28 星期日
 * Description:
 * 社会实践
 */
@Controller
@RequestMapping("/user")
public class SocialController {

    @Autowired
    private SocialService socialService;

    @GetMapping("/social")
    public String social(Model model) {
        model.addAttribute("socialList", socialService.findAll(0));
        return "user/socialPractice";
    }

    @ResponseBody
    @GetMapping("/social/more")
    public ResultVO more(Integer page) {
        return ResultVOUtils.success(socialService.findAll(page));
    }

    @GetMapping("/social/{socialId}")
    public String findOne(Model model,@PathVariable("socialId") Integer socialId) throws PartyException {
        model.addAttribute("social", socialService.findOne(socialId));
        return "user/socialPracticeDetail";
    }

//    @PostMapping("/save")
//    public ResultVO save(Social social) {
//        return ResultVOUtils.success(socialService.save(social));
//    }
}
