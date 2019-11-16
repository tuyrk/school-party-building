package com.cdutcm.party.exception;

import com.cdutcm.party.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 17:30 星期日
 * Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PartyException extends RuntimeException {
    private Integer code;

    public PartyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public PartyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
