package com.cdutcm.party.enums;

import lombok.Getter;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 22:29 星期日
 * Description:
 */
@Getter
public enum NewsEnum {
    INFORMATION(1,"国内外资讯"),
    POLICY(2,"政策方针"),
    SPEAK(3,"系列讲话"),
    ;

    private Integer code;
    private String type;

    NewsEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }
}
