package com.cdutcm.party.enums;

import lombok.Getter;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 17:32 星期日
 * Description:
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "请求参数不正确"),
    USER_NOT_EXIST(2, "该用户尚未注册"),
    USER_EXIST(3,"用户已注册"),
    USER_ERROR(4,"用户错误"),
    PASSWORD_ERROR(4,"密码错误"),

    PERSON_NOT_EXIST(5, "该人物尚未录入数据"),
    SOCIAL_NOT_EXIST(6, "该社会实践不存在"),

    NEWS_NOT_EXIST(7, "该热点不存在"),
    NEWS_TOP_NUM_ERROR(8, "数目必须大于等于1"),
    NEWS_TYPE_ERROR(9, "热点类型编号不正确（1,2,3）"),

    DISCUSS_SAVE_ERROR(10, "添加讨论失败"),
    DISCUSS_NOT_EXIST(11, "该讨论不存在"),
    DISCUSS_CONTENT_OVER(12, "评论超出字数"),

    COMMENT_SAVE_ERROR(13, "添加评论失败"),
    COMMENT_NOT_EXIST(14, "该评论不存在"),

    MAIL_ERROR(15, "邮箱错误"),
    MAIL_EXIST(16, "邮箱已注册"),

    COURSE_TYPE_ERROR(17, "课程类型编号不正确（1,2,3）"),
    COURSE_NOT_EXIST(18, "该课程不存在"),
    ELECTIVE_IS_EXIST(19, "已经添加该课程"),
    ELECTIVE_NOT_EXIST(20, "课程不存在"),
    NO_ANY_MORE(21, "没有更多了");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
