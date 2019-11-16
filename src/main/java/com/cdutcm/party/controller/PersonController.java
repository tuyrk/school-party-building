package com.cdutcm.party.controller;

import com.cdutcm.party.dataobject.Person;
import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.service.PersonService;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 18:47 星期日
 * Description:
 * 人物长廊
 */
@Controller
@RequestMapping("/user")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public String person(Model model) {
        model.addAttribute("personList", personService.findAll(0));
        return "user/longCorridor";
    }

    @ResponseBody
    @GetMapping("/person/more")
    public ResultVO all(Integer page) {
        return ResultVOUtils.success(personService.findAll(page));
    }

    @GetMapping("/person/{personId}")
    public String findOne(Model model, @PathVariable("personId") Integer personId) throws PartyException {
        model.addAttribute("person", personService.findOne(personId));
        return "user/longCorridorDetail";
    }

    @PostMapping("/person/save")
    public ResultVO save(Person person) {
        return ResultVOUtils.success(personService.save(person));
    }
}
