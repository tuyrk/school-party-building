package com.cdutcm.party.service;

import com.cdutcm.party.dataobject.User;
import com.cdutcm.party.form.UserForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 15:52 星期日
 * Description:
 * 用户
 */
public interface UserService {

    User findUser(Integer userId);

    User findOne(Integer userId);

    User save(User user,Integer userId);

    void saveHeadshot(MultipartFile file,Integer userId);

    boolean modify(Integer userId, String oldPassword, String newPassword);

    String sendCode(String mail);

    void add(User user);

    User findOne(String username,String password);

    void retrieve(String mail, String mailSession, String password);
}
