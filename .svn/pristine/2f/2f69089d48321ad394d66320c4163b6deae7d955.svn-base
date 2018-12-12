package com.gildata.byinterserver.controller;

import com.gildata.byinterserver.bean.Result;
import com.gildata.byinterserver.dto.DayDataCount;
import com.gildata.byinterserver.dto.ImportDetailDto;
import com.gildata.byinterserver.service.RealTimeMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 实时监控Controller
 * @Auther: shizh26250
 * @Date: 2018/11/27 11:26
 * @Description:
 */
@RestController
@RequestMapping("/realtimemonitor")
public class RealTimeMonitorController {

    @Autowired
    private RealTimeMonitorService realTimeMonitorService;

    /**
     * 获取今日导入数据列表
     * 功能描述: 
     *
     * @param:
     * @return: 
     * @auther: 
     * @date: 2018/11/29 19:29
     */
    @RequestMapping("/getImportDetail")
    public Result<List<ImportDetailDto>> getImportDetail() {
        Result<List<ImportDetailDto>> result = new Result<List<ImportDetailDto>>(Result.STATUS_SUCCESSED);

        result.setData(this.realTimeMonitorService.getImportDetail());
        return result;
    }

    /**
     * 获取数据源统计
     * 功能描述: 
     *
     * @param:
     * @return: 
     * @auther: 
     * @date: 2018/11/29 19:30
     */
    @RequestMapping("/getTotalDataByData")
    public Result<Map<String, Integer>> getTotalDataByData() {
        Result<Map<String, Integer>> result = new Result<Map<String, Integer>>(Result.STATUS_SUCCESSED);

        Map<String, Integer> map = new HashMap<>();
        map.put("import", this.realTimeMonitorService.getImportDataCountByDay());
        map.put("dataSource", this.realTimeMonitorService.getDataSourceCount());
        result.setData(map);

        return result;
    }

    /**
     * 获取近5天数据统计
     * 功能描述: 
     *
     * @param:
     * @return: 
     * @auther: 
     * @date: 2018/11/29 19:30
     */
    @RequestMapping("/getDataDetailCountBy5Day")
    public Result<List<DayDataCount>> getDataDetailCountBy5Day() {
        Result<List<DayDataCount>> result = new Result<List<DayDataCount>>(Result.STATUS_SUCCESSED);

        result.setData(this.realTimeMonitorService.getDataDetailCountBy5Day());
        return result;
    }

    /**
     * 获取今日数据量最多的10个域名
     * 功能描述: 
     *
     * @param:
     * @return: 
     * @auther: 
     * @date: 2018/11/29 19:30
     */
    @RequestMapping("/getTop10DetailByDay")
    public Result<List<DayDataCount>> getTop10DetailByDay() {
        Result<List<DayDataCount>> result = new Result<List<DayDataCount>>(Result.STATUS_SUCCESSED);

        result.setData(this.realTimeMonitorService.getTop10DetailByDay());
        return result;
    }

    /**
     * 获取实时数据
     * 功能描述: 
     *
     * @param:
     * @return: 
     * @auther: 
     * @date: 2018/11/29 19:30
     */
    @RequestMapping("/getRealTimeCount")
    public Result<Integer> getRealTimeCount(Integer minute) {
        Result<Integer> result = new Result<Integer>(Result.STATUS_SUCCESSED);

        if (null == minute || 0 >= minute) {
            result.setStatus(Result.STATUS_FIALED);
            result.setMsg("参数错误：minute不能为null或小于等于0");
            return result;
        }

        result.setData(this.realTimeMonitorService.getRealTimeCount(minute));
//        Random rand = new Random();
//        result.setData(rand.nextInt(100));

        return result;
    }

}
