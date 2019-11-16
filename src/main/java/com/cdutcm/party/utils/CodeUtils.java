package com.cdutcm.party.utils;

import java.util.Random;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/5 16:35 星期一
 * Description:
 */
public class CodeUtils {
    public static String getCode() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int x = 48 + random.nextInt(62);
            sb.append((char) (x + (x / 58) * 7 + (x / 84) * 6));
        }
        return sb.toString();
    }
}
