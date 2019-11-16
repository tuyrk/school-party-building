package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 22:59 星期日
 * Description:
 */
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<Object> findByTypeOrderByIdDesc(Integer type, Pageable pageable);

    List<News> findByOrderByIdDesc();
}
