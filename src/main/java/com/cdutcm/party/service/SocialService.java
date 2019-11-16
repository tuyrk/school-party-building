package com.cdutcm.party.service;

import com.cdutcm.party.dataobject.Social;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 19:14 星期日
 * Description:
 */
public interface SocialService {
    List<Object> findAll(Integer page);

    Social findOne(Integer socialId);

    Social save(Social social);

    void delete(Integer socialId);
}
