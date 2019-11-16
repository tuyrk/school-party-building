package com.cdutcm.party.handle;

import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.utils.ResultVOUtils;
import com.cdutcm.party.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/22 14:06 星期四
 * Description:
 */
@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler
    public ResultVO handle(PartyException e){
        return ResultVOUtils.error(e.getCode(), e.getMessage());
    }
}
