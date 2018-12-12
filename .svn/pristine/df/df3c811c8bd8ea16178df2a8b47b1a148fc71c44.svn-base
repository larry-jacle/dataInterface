package com.gildata.byinterserver.service;

import com.gildata.byinterserver.dto.DayDataCount;
import com.gildata.byinterserver.dto.ImportDetailDto;
import com.gildata.byinterserver.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 实时监控Service
 * @Auther: shizh26250
 * @Date: 2018/11/27 17:03
 * @Description:
 */
@Service
public class RealTimeMonitorService {

    @Autowired
    private JdbcTemplate jypConfigJdbcTemplate;

    /**
     * 获取今日导入数据列表
     * 功能描述:
     *
     * @param: 
     * @return: 
     * @auther: 
     * @date: 2018/11/27 20:30
     */
    public List<ImportDetailDto> getImportDetail() {
        List<ImportDetailDto> list = new ArrayList<>();
        ImportDetailDto dto = null;
        List<Map<String,Object>> res = null;
        String day = DateUtil.getToday();
        Object total, success;

        for (int i = 0; i < 24; i ++) {
            res = this.jypConfigJdbcTemplate.queryForList("SELECT SUM(C.count) AS total, SUM(IF(C.type=1,C.count,0)) AS success FROM (SELECT count, type FROM `usrbyzxsltj` WHERE `timestamp` LIKE '" + day + " " + (i < 10 ? "0" + i : i) + "%') C");
            for (Map<String, Object> map: res) {
                total = map.get("total");
                success = map.get("success");
                if (total != null && success != null) {
                    dto = new ImportDetailDto();
                    dto.setTime((i < 10 ? "0" + i : i) + ":00:00");
                    dto.setTotal(new Integer(total.toString()));
                    dto.setSuccessCnt(new Integer(success.toString()));
                    res = this.jypConfigJdbcTemplate.queryForList("SELECT msgtype, COUNT(1) AS cnt FROM `usrbyzxxq` WHERE `timestamp` LIKE '" + day + " " + (i < 10 ? "0" + i : i) + "%' GROUP BY msgtype");
                    for (Map<String, Object> m: res) {
                        switch (new Integer(m.get("msgtype").toString())) {
                            case 1:
                                dto.setNewsCnt(new Integer(m.get("cnt").toString()));
                                break;
                            case 2:
                                dto.setWeiboCnt(new Integer(m.get("cnt").toString()));
                                break;
                            case 3:
                                dto.setWeixinCnt(new Integer(m.get("cnt").toString()));
                                break;
                            case 4:
                                dto.setZhihuCnt(new Integer(m.get("cnt").toString()));
                                break;
                            default:
                                break;
                        }
                    }
                    list.add(dto);
                }
            }
        }

        return list;
    }

    /**
     * 获取今日数据导入条数
     * 功能描述: 
     *
     * @param:
     * @return: 
     * @auther: 
     * @date: 2018/11/27 20:31
     */
    public int getImportDataCountByDay() {
        String day = DateUtil.getToday();
        List<Map<String,Object>> res = this.jypConfigJdbcTemplate.queryForList("SELECT COUNT(1) AS cnt FROM `usrbyzxxq` WHERE `timestamp` LIKE '" + day + "%'");
        return new Integer(res.get(0).get("cnt").toString());
    }

    /**
     * 获取数据源个数
     * 功能描述: 
     *
     * @param: 
     * @return: 
     * @auther: 
     * @date: 2018/11/27 20:31
     */
    public int getDataSourceCount() {
        String day = DateUtil.getToday();
        List<Map<String,Object>> res = this.jypConfigJdbcTemplate.queryForList("SELECT COUNT(DISTINCT(`host`)) AS cnt FROM `usrbyzxxq`");
        return new Integer(res.get(0).get("cnt").toString());
    }
    
    /**
     * 获取近5天数据统计
     * 功能描述: 
     *
     * @param: 
     * @return: 
     * @auther: 
     * @date: 2018/11/27 20:31
     */
    public List<DayDataCount> getDataDetailCountBy5Day() {
        List<DayDataCount> list = new ArrayList<>();
        DayDataCount ddc = null;
        List<Map<String,Object>> res = null;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String day = null;

        for (int i = 0; i < 5; i ++) {
            ddc = new DayDataCount();
            day = DateUtil.format(c.getTime(), DateUtil.format1);
            res = this.jypConfigJdbcTemplate.queryForList("SELECT (SELECT COUNT(1) FROM (SELECT COUNT(1) AS h FROM `usrbyzxxq` WHERE `timestamp` LIKE '" + day + "%' GROUP BY `host`) H) AS hostCnt, (SELECT COUNT(1) FROM (SELECT COUNT(1) AS w FROM `usrbyzxxq` WHERE `timestamp` LIKE '" + day + "%' GROUP BY `weburl`) W) AS urlCnt, COUNT(1) AS dataCnt FROM `usrbyzxxq` WHERE `timestamp` LIKE '" + day + "%'");
            ddc.setDate(day);
            ddc.setHostCnt(new Integer(res.get(0).get("hostCnt").toString()));
            ddc.setUrlCnt(new Integer(res.get(0).get("urlCnt").toString()));
            ddc.setDataCnt(new Integer(res.get(0).get("dataCnt").toString()));
            list.add(ddc);
            c.add(Calendar.DAY_OF_YEAR, -1);
        }

        return list;
    }

    /**
     * 获取今日数据量最多的10个域名
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther:
     * @date: 2018/11/27 20:32
     */
    public List<DayDataCount> getTop10DetailByDay() {
        List<DayDataCount> list = new ArrayList<>();
        DayDataCount ddc = null;
        String day = DateUtil.getToday();
        List<Map<String,Object>> res = this.jypConfigJdbcTemplate.queryForList("SELECT C.`host` AS `host`, C.dataCnt AS dataCnt FROM (SELECT `host`, COUNT(1) AS dataCnt FROM `usrbyzxxq` WHERE `timestamp` LIKE '" + day + "%' GROUP BY `host`) C ORDER BY C.dataCnt DESC LIMIT 10");
        int i = 0;

        for (Map<String, Object> map: res) {
            i ++;
            ddc = new DayDataCount();
            ddc.setHost(map.get("host").toString());
            ddc.setDataCnt(new Integer(map.get("dataCnt").toString()));
            list.add(ddc);
            if (i == 10) {
                break;
            }
        }

        return list;
    }

    /**
     * 获取实时数据
     * @param minute
     * @return
     */
    public int getRealTimeCount(int minute) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, -minute);

        String start = DateUtil.format(c.getTime(), "yyyy-MM-dd HH:mm");
        String end = DateUtil.format(date, "yyyy-MM-dd HH:mm");
        List<Map<String,Object>> res = this.jypConfigJdbcTemplate.queryForList("SELECT COUNT(1) AS cnt FROM `usrbyzxxq` WHERE `timestamp` > '" + start + "' AND `timestamp` <= '" + end + "'");

        return new Integer(res.get(0).get("cnt").toString());
    }
}
