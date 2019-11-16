package com.cdutcm.party.repository;

import com.cdutcm.party.PartyApplicationTests;
import com.cdutcm.party.dataobject.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserRepositoryTest extends PartyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("\"123\"");
        user.setPhone("18382471383");
        user.setPassword("\"123");
        user.setMail("76656416@qq.com");

        User saveResult = userRepository.save(user);
        assertNotNull(saveResult);
    }

    @Test
    public void findByUsernameAndPassword() {
        User findResult = userRepository.findByUsernameAndPassword("123", "123");
        assertNotNull(findResult);
    }
}