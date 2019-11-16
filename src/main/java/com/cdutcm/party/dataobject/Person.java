package com.cdutcm.party.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 18:31 星期日
 * Description:
 * 人物长廊
 */
@Entity
@Data
public class Person {
    /* 人物长廊ID */
    @Id
    @GeneratedValue
    private Integer id;

    /* 名字 */
    private String username;

    /* 头衔 */
    private String title;

    /* 头像 */
    private String headshot;

    /* 描述 */
    public String description;
}
