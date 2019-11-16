package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.Discuss;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 10:13 星期一
 * Description:
 */
public interface DiscussRepository extends JpaRepository<Discuss,Integer> {
    List<Discuss> findAllByOrderByIdDesc(Pageable pageable);

    List<Discuss> findAllByUsernameOrderByIdDesc(String username, Pageable pageable);
}
