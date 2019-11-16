package com.cdutcm.party.dataobject;

import com.cdutcm.party.utils.serializer.DateFormatSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 15:41 星期日
 * Description:
 */
@Entity
@Data
public class User {
    /* 用户ID */
    @Id
    @GeneratedValue
    private Integer id;

    /* 用户头像 */
    private String headshot;

    /* 用户名 */
    private String username;

    /* 用户密码 */
    private String password;

    /* 姓名 */
    private String name;

    /* 出生日期 */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;

    /* 民族 */
    private String nation;

    /* 政治面貌 */
    private String political;

    /* 手机号 */
    private String phone;

    /* 邮箱 */
    private String mail;

    /* 学校 */
    private String school;

    /* 学院 */
    private String college;

    /* 班级 */
    private String classes;

    /* 确定为积极分子时间 */
    private Date positive;

    /* 转预备时间 */
    private Date prepare;

    /* 转正时间 */
    private Date official;

    public User() {
    }

    public User(Integer id, String headshot, String username, String mail) {
        this.id = id;
        this.headshot = headshot;
        this.username = username;
        this.mail = mail;
    }
}











