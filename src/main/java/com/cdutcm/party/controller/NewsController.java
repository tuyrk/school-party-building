package com.cdutcm.party.controller;

import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.service.NewsService;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 0:30 星期一
 * Description:
 * 热点关注
 */
@Controller
@RequestMapping("/user")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/index")
    public String index(Model model) throws PartyException {
        model.addAttribute("news", newsService.findPageDesc(0, 2));
        return "user/index";
    }

    @GetMapping("/news")
    public String news(Model model) throws PartyException {
        model.addAttribute("newsList", newsService.findAll(1, 0));
        return "user/concernMore";
    }

    @ResponseBody
    @GetMapping("/news/more")
    public ResultVO more(Integer type, Integer page) throws PartyException {
        return ResultVOUtils.success(newsService.findAll(type, page));
    }

    @GetMapping("/news/{newsId}")
    public String findOne(Model model, @PathVariable("newsId") Integer newsId) throws PartyException {
        model.addAttribute("news", newsService.findOne(newsId));
        return "user/concernMoreDetail";
    }


//    @PostMapping("/save")
//    public ResultVO save(News news) {
//        return ResultVOUtils.success(newsService.save(news));
//    }
}
