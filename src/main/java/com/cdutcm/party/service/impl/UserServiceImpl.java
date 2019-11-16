package com.cdutcm.party.service.impl;

import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.dataobject.User;
import com.cdutcm.party.enums.ResultEnum;
import com.cdutcm.party.repository.UserRepository;
import com.cdutcm.party.service.UserService;
import com.cdutcm.party.utils.CodeUtils;
import com.cdutcm.party.utils.CopyClassUtils;
import com.cdutcm.party.utils.SendEmailByQQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 15:55 星期日
 * Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(Integer userId) {
        return userRepository.findUser(userId);
    }

    @Override
    public User findOne(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User save(User user, Integer userId) {
        User userData = userRepository.findOne(userId);
        if (userData == null) {
            throw new PartyException(ResultEnum.USER_ERROR);
        }
        userData = (User) CopyClassUtils.copyFieldValues(userData, user);
        log.info("【个人信息】个人信息：user = {}  ", user);
        return userRepository.save(userData);
    }

    @Override
    @Transactional
    public void saveHeadshot(MultipartFile file,Integer userId) {
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            URL url = this.getClass().getClassLoader().getResource("static/img/headshot/");
            String image = userId + filename.substring(filename.lastIndexOf('.'));
            try {
                assert url != null;
                File img = new File(URLDecoder.decode(url.getPath(), "UTF-8") + image);
                System.out.println("img = " + img);
                //img = file:\E:\党建信息化\后台代码\party\target\party-0.0.1-SNAPSHOT.jar!\BOOT-INF\classes!\static\img\headshot\6.jpg
                //img = E:\党建信息化\后台代码\party\target\classes\static\img\headshot\6.jpg
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(img.getAbsoluteFile()));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            userRepository.setHeadshot("/img/headshot/"+image, userId);
        }
    }

    @Override
    public boolean modify(Integer userId, String oldPassword, String newPassword) {
        User user = userRepository.findOne(userId);
        oldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!user.getPassword().equals(oldPassword)) {
            throw new PartyException(ResultEnum.PASSWORD_ERROR);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        userRepository.save(user);
        return true;
    }

    @Override
    public String sendCode(String mail) {
        String code = CodeUtils.getCode();
        String content = "<div style=\"background-color:#d0d0d0;background-image:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg.png);text-align:center;padding:20px\"><div class=\"x_mmsgLetter\"style=\"max-width:350px;margin:0 auto;color:rgb(51,51,51);background-color:rgb(255,255,255);border:0 solid rgb(170,170,170);border-radius:5px;font-family:Verdana,sans-serif,serif,EmojiFont;\"><div class=\"x_mmsgLetterHeader\"style=\"height:23px;background:url(http://weixin.qq.com/zh_CN/htmledition/images/weixin/letter/mmsgletter_2_bg_topline.png) repeat-x 0 0\"></div><div class=\"x_mmsgLetterContent\"style=\"text-align:left;padding:30px;font-size:14px;line-height:1.5;background:url(http://cdutcm.edu.cn/images/index_top_1.gif) no-repeat top center;background-size:65% 10.5%;\"><div><br><p>您好!</p><p>感谢您使用高校党建信息化平台<br>您正在注册账号。请回填如下6位验证码：</p><p class=\"x_mmsgLetterDigital\"style=\"font-size:22px;letter-spacing:10px;\">" + code + "</p><p>验证码在30分钟内有效，30分钟后需要重新发送验证码</p></div><div class=\"x_mmsgLetterInscribe\"style=\"padding:40px 0 0\"><img data-imagetype=\"External\"src=\"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=328327349,855323749&fm=27&gp=0.jpg\"class=\"x_mmsgAvatar\"style=\"float:left\"height=\"45px\"width=\"45px\"><div class=\"x_mmsgSender\"style=\"margin:0 0 0 54px\"><p class=\"x_mmsgName\"style=\"margin:0 0 10px\">TYK</p><p class=\"x_mmsgInfo\"style=\"font-size:12px;margin:0;line-height:1.2\">开发人员<br><a href=\"mailto:766564616@qq.com\"target=\"_blank\"rel=\"noopener noreferrer\"style=\"color:#407700\">766564616@qq.com</a></p></div></div></div><div class=\"x_mmsgLetterFooter\"style=\"margin:16px;text-align:center;font-size:12px;color:#888\"><img data-imagetype=\"External\"data-imageerror=\"RelWithoutBase\"originalsrc=\"/cgi-bin/readtemplate?sid=$SID$&amp;loc=pushmail1,weixiniphone,show,44\"src=\"data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAEALAAAAAABAAEAAAIBTAA7\"onload=\"InlineImageLoader.GetLoader().Load(this)\"style=\"width:1px;height:1px\"crossorigin=\"anonymous\"><img data-imagetype=\"External\"src=\"http://weixin.qq.com/cgi-bin/reportforpromote?uin=$RCPTUIN$&amp;sdate=$SDATE$&amp;tver=$TVER$\"style=\"width:1px;height:1px\"></div></div></div>";
        if (SendEmailByQQ.SendEmail(mail, code, content)) {
            return code;
        }
        return null;
    }

    @Override
    public void add(User user) {
        User userMail = userRepository.findByMail(user.getMail());
        if (userMail != null) {//该邮箱已经注册
            log.info("【用户注册】user = (已注册)" + user);
            throw new PartyException(ResultEnum.MAIL_EXIST);
        }
        User userData = userRepository.findByUsername(user.getUsername());
        if (userData != null) {
            throw new PartyException(ResultEnum.USER_EXIST);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setHeadshot("/img/headshot/0.jfif");
        log.info("【用户注册】用户注册：user = {}  ", userRepository.save(user));
    }

    @Override
    public User findOne(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new PartyException(ResultEnum.USER_NOT_EXIST);
        }
        return user;
    }

    @Override
    public void retrieve(String mail, String mailSession, String password) {
        if(mail.trim().equals(mailSession)){
            User user = userRepository.findByMail(mail);
            if (user == null){
                throw new PartyException(ResultEnum.MAIL_ERROR);
            }
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            userRepository.save(user);
            return;
        }
        throw new PartyException(ResultEnum.MAIL_ERROR);
    }
}
