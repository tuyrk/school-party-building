package com.cdutcm.party.enums;

import lombok.Getter;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/22 14:50 星期四
 * Description:
 */
@Getter
public enum CommentEnum {
    DISCUSS(1, "讨论交流"),
    COURSE(2, "经典课程"),
    STUDY(3, "学习感想"),
    GOAL(4, "任务目标"),;

    private Integer code;
    private String message;

    CommentEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
