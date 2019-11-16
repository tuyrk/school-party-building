package com.cdutcm.party.service;

import com.cdutcm.party.dataobject.News;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 0:09 星期一
 * Description:
 */
public interface NewsService {
    /* 查找最后两条记录 */
    List<News> findPageDesc(Integer page, Integer size);

    List<Object> findAll(Integer type, Integer page);

    News findOne(Integer newsId);

    News save(News news);

    void delete(Integer newsId);
}
