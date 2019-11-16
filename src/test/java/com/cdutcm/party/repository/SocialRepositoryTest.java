package com.cdutcm.party.repository;

import com.cdutcm.party.PartyApplicationTests;
import com.cdutcm.party.dataobject.Social;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class SocialRepositoryTest extends PartyApplicationTests {

    @Autowired
    private SocialRepository socialRepository;

    @Test
    public void findOne() {
        Social social = socialRepository.findOne(1);
        System.out.println("social = " + social);
        assertNotNull(social);
    }

    @Test
    public void findList() {
        List<Social> socialList = socialRepository.findAll();
        for (Social social : socialList) {
            System.out.println("social = " + social);
        }
        assertNotEquals(0, socialList.size());
    }

}