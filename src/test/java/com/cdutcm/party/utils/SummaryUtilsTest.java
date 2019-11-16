package com.cdutcm.party.utils;

import com.cdutcm.party.dataobject.News;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SummaryUtilsTest {

    @Test
    public void getSummary() {
        List<Object> objectList = new ArrayList<Object>();
        News news = new News();
        news.setId(1);
        news.setTitle("setTitle");
        news.setHeadshot("setHeadshot");
        news.setContent("当地时间2018年2月4日，俄罗斯莫斯科，当地遭大雪袭击。据路透社报道，莫斯科3日一天的降雪量就达到了月平均降雪量的一半，打破了1954年的降雪记录。当地官员称有2000多棵大树被压塌，多个航班被延误。（图片来源：视觉中国）");
        news.setTime(new Date());
        news.setSource("setSource");
        news.setType(1);
        objectList.add(news);
        objectList = new SummaryUtils().getSummary(objectList);

        for (int i = 0; i < objectList.size(); i++) {
            System.out.println("getContent = " + ((News) objectList.get(i)).getContent());
        }
    }
}