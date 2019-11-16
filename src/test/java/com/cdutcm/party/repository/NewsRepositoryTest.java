package com.cdutcm.party.repository;

import com.cdutcm.party.PartyApplicationTests;
import com.cdutcm.party.dataobject.News;
import com.cdutcm.party.enums.NewsEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class NewsRepositoryTest extends PartyApplicationTests {

    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void save() {
        for (int i = 0; i < 10; i++) {
            News news = new News();
            news.setTitle(String.valueOf((i + 1)));
            news.setHeadshot(String.valueOf((i + 1)));
            news.setContent(String.valueOf((i + 1)));
            news.setSource(String.valueOf((i + 1)));
            news.setType(i % 3 + 1);
            newsRepository.save(news);
        }
    }

    @Test
    public void findByTypeOrderByTimeDesc() {
        Pageable pageable = new PageRequest(0, 2);
        List<Object> newsList = newsRepository.findByTypeOrderByIdDesc(NewsEnum.INFORMATION.getCode(),pageable);
        for (Object news : newsList) {
            System.out.println("news = " + ((News)news).getId());
        }
    }

    @Test
    public void findAllLast2() {
        Sort sort = new Sort(Sort.Direction.DESC, "time");
        Pageable pageable = new PageRequest(0, 2, sort);
        Page<News> newsList = newsRepository.findAll(pageable);
        for (News news : newsList.getContent()) {
            System.out.println("news = " + news);
        }
    }
}