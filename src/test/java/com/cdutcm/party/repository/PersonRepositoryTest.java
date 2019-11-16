package com.cdutcm.party.repository;

import com.cdutcm.party.PartyApplicationTests;
import com.cdutcm.party.dataobject.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class PersonRepositoryTest extends PartyApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findOne() {
        Person person = personRepository.findOne(1);
        System.out.println("person = " + person);
        assertNotNull(person);
    }

    @Test
    public void findList() {
        List<Person> personList = personRepository.findAll();
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        assertNotEquals(0, personList.size());
    }

}