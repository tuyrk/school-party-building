package com.cdutcm.party.vo;

import lombok.Data;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 17:16 星期日
 * Description:
 */
@Data
public class ResultVO<T> {
    /* 错误码 */
    private Integer code;

    /* 错误信息 */
    private String msg;

    /* 返回的内容 */
    private T data;
}
