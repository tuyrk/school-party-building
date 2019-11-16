package com.cdutcm.party.service.impl;

import com.cdutcm.party.exception.PartyException;
import com.cdutcm.party.dataobject.Social;
import com.cdutcm.party.enums.ResultEnum;
import com.cdutcm.party.repository.SocialRepository;
import com.cdutcm.party.service.SocialService;
import com.cdutcm.party.utils.SummaryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/2/4 19:15 星期日
 * Description:
 */
@Service
@Slf4j
public class SocialServiceImpl implements SocialService {

    @Autowired
    private SocialRepository socialRepository;

    @Override
    public List<Object> findAll(Integer page) {
        Pageable pageable = new PageRequest(page, 10);
        List<Object> socialList = socialRepository.findByOrderByIdDesc(pageable);
        if (socialList.size() > 0) {
            return new SummaryUtils().getSummary(socialList);
        }
//        throw new PartyException(ResultEnum.NO_ANY_MORE);
        return null;
    }

    @Override
    public Social findOne(Integer socialId) {
        Social social = socialRepository.findOne(socialId);
        if (social == null) {
            log.error("【社会实践】该社会实践不存在 socialId = {}", socialId);
            throw new PartyException(ResultEnum.SOCIAL_NOT_EXIST);
        }
        return social;
    }

    @Override
    public Social save(Social social) {
        return socialRepository.save(social);
    }

    @Override
    public void delete(Integer socialId) {
        findOne(socialId);
        socialRepository.delete(socialId);
    }
}
