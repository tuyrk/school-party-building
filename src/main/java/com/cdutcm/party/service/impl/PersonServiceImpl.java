package com.cdutcm.party.service.impl;

import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.dataobject.Person;
import com.cdutcm.party.enums.ResultEnum;
import com.cdutcm.party.repository.PersonRepository;
import com.cdutcm.party.service.PersonService;
import com.cdutcm.party.utils.SummaryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 18:44 星期日
 * Description:
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAll(Integer page) {
        Pageable pageable = new PageRequest(page, 10);
        List<Object> personList = personRepository.findByOrderByIdDesc(pageable);
        if (personList.size() > 0) {
            return new SummaryUtils().getSummary(personList);
        }
//        throw new PartyException(ResultEnum.NO_ANY_MORE);
        return null;
    }

    @Override
    public Person findOne(Integer personId) {
        Person person = personRepository.findOne(personId);
        if (person == null) {
            log.error("【人物长廊】该人物不存在 personId = {}", personId);
            throw new PartyException(ResultEnum.PERSON_NOT_EXIST);
        }
        return person;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Integer personId) {
        findOne(personId);
        personRepository.delete(personId);
    }
}
