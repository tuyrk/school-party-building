package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 18:33 星期日
 * Description:
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Object> findByOrderByIdDesc(Pageable pageable);
}