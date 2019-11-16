package com.cdutcm.party.repository;

import com.cdutcm.party.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 15:49 星期日
 * Description:
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select new User (id,headshot,username,mail) from User where id = ?1")
    User findUser(Integer userId);

    User findByMail(String mail);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    @Modifying
    @Query("update User u set u.headshot = ?1 where u.id = ?2")
    void setHeadshot(String headshot , Integer userId);
}
