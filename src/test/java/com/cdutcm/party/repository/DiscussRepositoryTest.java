package com.cdutcm.party.repository;

import com.cdutcm.party.PartyApplicationTests;
import com.cdutcm.party.dataobject.Discuss;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class DiscussRepositoryTest extends PartyApplicationTests {

    @Autowired
    private DiscussRepository discussRepository;

    @Test
    public void save() {
        for (int i = 0; i < 10; i++) {
            Discuss discuss = new Discuss();
            discuss.setUsername("tyk_" + (i + 1));
            discuss.setHeadshot(String.valueOf((i + 1)));
            discuss.setContent(String.valueOf((i + 1)));
            discuss.setImage(String.valueOf((i + 1)));
            discussRepository.save(discuss);
        }
//        Discuss discuss = discussRepository.findOne(1);
//        System.out.println("discuss = " + discuss);
//        discuss.setCount(discuss.getCount() + 1);
//        discussRepository.save(discuss);
    }

    @Test
    public void findByOrderByIdDesc() {
        Pageable pageable = new PageRequest(0, 10);
        List<Discuss> discussList = discussRepository.findAllByOrderByIdDesc(pageable);
        for (Discuss discuss : discussList) {
            System.out.println("discuss = " + discuss);
        }
    }

    @Test
    public void findByName() {
        Pageable pageable = new PageRequest(0, 10);
        List<Discuss> discussList = discussRepository.findAllByUsernameOrderByIdDesc("tyk_1",pageable);
        for (Discuss discuss : discussList) {
            System.out.println("discuss = " + discuss);
        }
    }
}