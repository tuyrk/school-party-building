package com.cdutcm.party.service.impl;

import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.dataobject.News;
import com.cdutcm.party.enums.ResultEnum;
import com.cdutcm.party.repository.NewsRepository;
import com.cdutcm.party.service.NewsService;
import com.cdutcm.party.utils.SummaryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 0:11 星期一
 * Description:
 */
@Service
@Slf4j
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> findPageDesc(Integer page, Integer size) {
        if (size < 1) {
            log.error("【热点关注】TOP数目必须大于等于1 size = {}", size);
            throw new PartyException(ResultEnum.NEWS_TOP_NUM_ERROR);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, size, sort);
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.getContent();
    }

    @Override
    public List<Object> findAll(Integer type, Integer page) {
        if (0 > type || type > 3) {
            log.error("【热点关注】类目编号不正确（1,2,3）type = {}", type);
            throw new PartyException(ResultEnum.NEWS_TYPE_ERROR);
        }
        Pageable pageable = new PageRequest(page, 10);
        List<Object> newsList = newsRepository.findByTypeOrderByIdDesc(type, pageable);
        if (newsList.size() > 0) {
            return new SummaryUtils().getSummary(newsList);
        }
//        throw new PartyException(ResultEnum.NO_ANY_MORE);
        return null;
    }

    @Override
    public News findOne(Integer newsId) {
        News news = newsRepository.findOne(newsId);
        if (news == null) {
            log.error("【热点关注】该热点关注不存在 newsId = {}", newsId);
            throw new PartyException(ResultEnum.NEWS_NOT_EXIST);
        }
        return news;
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public void delete(Integer newsId) {
        findOne(newsId);
        newsRepository.delete(newsId);
    }
}
