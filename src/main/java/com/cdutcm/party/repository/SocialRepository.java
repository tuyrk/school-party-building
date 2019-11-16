package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.Social;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 19:01 星期日
 * Description:
 */
public interface SocialRepository extends JpaRepository<Social, Integer> {
    List<Object> findByOrderByIdDesc(Pageable pageable);
}
