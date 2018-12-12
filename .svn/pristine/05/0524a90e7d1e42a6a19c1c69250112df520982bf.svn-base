package com.gildata.byinterserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiChao on 2018/11/20
 */

@Service
public class LoadDataService {

    public static Map<String,String> DATAS = new HashMap<>();

    public static Map<String,String> DMS = new HashMap<>();

    @Autowired
    @Qualifier("jypConfigJdbcTemplate")
    private JdbcTemplate jypConfigJdbcTemplate;

    public void init(){
        DATAS = getData();
        DMS = getDM();
    }



    private HashMap<String,String> getData(){
        HashMap<String,String> data = new HashMap<>();
        List<Map<String,Object>> res =  jypConfigJdbcTemplate.queryForList("select MTCC,SJYBM,LMLB,ZLMC,ZYLJ from usrYBZXSJYB");
        for(Map<String,Object> map : res){
            String MTCC = map.get("MTCC") == null ? "" : map.get("MTCC").toString();
            String SJYBM = map.get("SJYBM") == null ? "" : map.get("SJYBM").toString();
            String LMLB = map.get("LMLB") == null ? "" : map.get("LMLB").toString();
            String ZLMC = map.get("ZLMC") == null ? "" : map.get("ZLMC").toString();
            String ZYLJ = map.get("ZYLJ") == null ? "" : map.get("ZYLJ").toString();
            data.put( ZLMC + "^^" + ZYLJ,MTCC + "^^" + SJYBM + "^^" + LMLB);
        }
        return data;
    }


    private HashMap<String,String> getDM(){
        HashMap<String,String> dm = new HashMap<>();
        List<Map<String,Object>> res =  jypConfigJdbcTemplate.queryForList("select DM,CLMS from usrZXCLB where LB = 4");
        for(Map<String,Object> map : res){
            String DM = map.get("DM") == null ? "" : map.get("DM").toString();
            String CLMS = map.get("CLMS") == null ? "" : map.get("CLMS").toString();
            dm.put(DM,CLMS);
        }
        return dm;
    }


}
