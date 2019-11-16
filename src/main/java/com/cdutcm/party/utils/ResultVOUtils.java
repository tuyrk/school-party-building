package com.cdutcm.party.utils;

import com.cdutcm.party.vo.ResultVO;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 17:21 星期日
 * Description:
 */
public class ResultVOUtils {
    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static ResultVO error(Integer code,String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }
}
