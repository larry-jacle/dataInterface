package com.gildata.byinterserver.service;

import com.gildata.byinterserver.bean.NewsItem;
import com.gildata.byinterserver.domain.UsrBYZXSLTJ;
import com.gildata.byinterserver.repository.UsrBYZXSLTJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenchen on 2018/11/27.
 */

@Service
public class UsrBYZXSLTJServiceImpl implements UsrBYZXSLTJService{

    @Autowired
    private UsrBYZXSLTJRepository usrBYZXSLTJRepository;

    @Override
    public void save(List<UsrBYZXSLTJ> usrBYZXSLTJList) {
        usrBYZXSLTJRepository.saveAll(usrBYZXSLTJList);
    }

    @Override
    public UsrBYZXSLTJ findOne(Long id) {
        return usrBYZXSLTJRepository.getOne(id);
    }

    @Override
    public List<UsrBYZXSLTJ> findAll() {
        return usrBYZXSLTJRepository.findAll();
    }

    @Override
    public List findLastestSevenDaysData() {
        List<Object[]> list = usrBYZXSLTJRepository.findLastestSevenDaysData();

        List<NewsItem> newsItems = new ArrayList<>();
        for (Object[] object : list) {
            NewsItem newsItem = new NewsItem();
            newsItem.setNewsDate(String.valueOf(object[0]));
            newsItem.setTotalNewsNum(Integer.valueOf(String.valueOf(object[1])));
            newsItem.setSuccessNewsNum(Integer.valueOf(String.valueOf(object[2])));
            newsItem.setXinwen(Integer.valueOf(String.valueOf(object[3])));
            newsItem.setWeibo(Integer.valueOf(String.valueOf(object[4])));
            newsItem.setWeixin(Integer.valueOf(String.valueOf(object[5])));
            newsItem.setZhihu(Integer.valueOf(String.valueOf(object[6])));
            newsItems.add(newsItem);
        }

        return newsItems;
    }

    @Override
    public List<Object[]> findLastestSevenDaysSuccessData() {
        return usrBYZXSLTJRepository.findLastestSevenDaysSuccessData();
    }

    @Override
    public Object[] findTodayNewsData() {
        return usrBYZXSLTJRepository.findTodayNewsData().get(0);
    }
}
