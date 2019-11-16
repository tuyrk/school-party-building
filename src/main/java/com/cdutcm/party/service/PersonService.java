package com.cdutcm.party.service;

import com.cdutcm.party.dataobject.Person;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 18:43 星期日
 * Description:
 */
public interface PersonService {
    List<Person> findAll(Integer page);

    Person findOne(Integer personId);

    Person save(Person person);

    void delete(Integer personId);
}
