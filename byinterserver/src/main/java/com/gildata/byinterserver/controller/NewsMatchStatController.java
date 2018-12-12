package com.gildata.byinterserver.controller;

import com.alibaba.fastjson.JSON;
import com.gildata.byinterserver.bean.NewsItem;
import com.gildata.byinterserver.service.UsrBYZXSLTJService;
import com.gildata.byinterserver.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博约接口新闻统计Controller
 * Created by chenchen on 2018/11/16.
 */
@Controller
public class NewsMatchStatController {
    private static final Logger logger = LoggerFactory.getLogger(NewsMatchStatController.class);

    @Autowired
    private UsrBYZXSLTJService usrBYZXSLTJService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toMain(Model model) {
        List<NewsItem> newsItems = usrBYZXSLTJService.findLastestSevenDaysData();
        model.addAttribute("newsItems", newsItems);

        return "businessdata/newsmatchstat";
    }

    @RequestMapping(value = "/stat/gauge", method = RequestMethod.GET)
    @ResponseBody
    public String toGauge(Model model) {
        Object[] object = usrBYZXSLTJService.findTodayNewsData();

        int total = Integer.parseInt(String.valueOf(object[1]==null?0:object[0]));
        int success = Integer.parseInt(String.valueOf(object[1]==null?0:object[1]));
        int fail = Integer.parseInt(String.valueOf(object[1]==null?0:object[2]));
        String successRatio = "0.00";
        if (total != 0) {
            successRatio = NumberUtil.getRatio(success, total);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("total", NumberUtil.getNumberFormat(total));
        result.put("success", NumberUtil.getNumberFormat(success));
        result.put("fail", NumberUtil.getNumberFormat(fail));
        result.put("successRatio", successRatio);

        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/stat/bar", method = RequestMethod.GET)
    @ResponseBody
    public String toBar(Model model) {
        List<Object[]> list = usrBYZXSLTJService.findLastestSevenDaysSuccessData();

        List<String> dateList = new ArrayList<>();
        List<Integer> successNumList = new ArrayList<>();
        for (Object[] object : list) {
            dateList.add(String.valueOf(object[0]));
            successNumList.add(Integer.valueOf(String.valueOf(object[1])));
        }

        Map<String, List> result = new HashMap<>();
        result.put("dates", dateList);
        result.put("successNums", successNumList);

        return JSON.toJSONString(result);
    }

}
