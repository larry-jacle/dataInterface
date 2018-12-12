package com.gildata.byinterserver.controller;

import com.gildata.byinterserver.bean.NewsItem;
import com.gildata.byinterserver.quartz.Init;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 页面跳转Controller
 * @Auther: shizh26250
 * @Date: 2018/11/27 11:28
 * @Description:
 */
@Controller
public class PageController {

    @RequestMapping(value = "/realtimemonitor", method = RequestMethod.GET)
    public String toRealtimemonitor(Model model) {
        return "businessdata/realtimemonitor.html";
    }

    @RequestMapping(value = "/datasourcemonitor", method = RequestMethod.GET)
    public String toDataSourcemonitor(Model model) {
        return "businessdata/datasourcemonitor.html";
    }

}
