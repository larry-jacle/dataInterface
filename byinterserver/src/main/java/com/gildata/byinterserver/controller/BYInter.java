package com.gildata.byinterserver.controller;

import com.gildata.byinterserver.exception.InvalidRequestException;
import com.gildata.byinterserver.exception.UnauthorizedException;
import com.gildata.byinterserver.service.LoadDataService;
import com.gildata.byinterserver.service.WriteDataService;
import com.gildata.byinterserver.utils.DateUtil;
import com.gildata.byinterserver.utils.SignatureUtil;
import com.gildata.byinterserver.utils.UuidUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LiChao on 2018/11/8
 */

@RestController
public class BYInter {


    @Autowired
    private LoadDataService loadDataService;

    @Autowired
    private WriteDataService writeDataService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final HashSet paramSet = new HashSet<String>(){{
        add("tag");
        add("timestamp");
        add("sign");
        add("data");
        add("datasize");
        add("datatype");
    }};

    private static final HashSet dataSet = new HashSet<String>(){{
        add("id");
        add("host");
        add("weburl");
        add("sector");
        add("msgtype");
        add("msgsubtype");
        add("msgsourceurl");
        add("title");
        add("subtitle");
        add("content");
        add("contentimglinks");
        add("author");
        add("rfflag");
        add("digester");
        add("pushdate");
        add("pushtime");
        add("address_s");
        add("sourceurl");
        add("quote_author");
        add("quote_content");
        add("quote_date");
        add("zzs");
        add("hfs");
        add("djs");
    }};


    @PostMapping("/api/sendPublicMsgs")
    public ResponseEntity<?> sendPublicMsgs(@RequestBody String param){
        String beginDateStr = simpleDateFormat.format(new Date());
        long begintime = System.currentTimeMillis();
        int datasize = 0;
        int datacount = 0;
        JsonParser parser=new JsonParser();  //创建JSON解析器
        JsonObject object = null;
        Map<String,Object> fail = new HashMap<>();
        fail.put("code",500);
        Map<String,Object> success = new HashMap<>();
        success.put("code",200);
        try{
            object=(JsonObject) parser.parse(param);
            datasize = object.toString().length();
            datacount = object.get("data").getAsJsonArray().size();
        }catch (Exception e){
            fail.put("errormsg","参数格式有误");
            String json = new Gson().toJson(fail);
            logger.error("request failed:  param ------------>" + param + " ,result ------------> " + json);
            return new ResponseEntity<String>(json,HttpStatus.OK);
        }
        List<JsonObject> datas;
        try{
            datas = parseParam(object);
        }catch (Exception e){
            fail.put("errormsg",e.getMessage());
            String json = new Gson().toJson(fail);
            logger.error("request failed:  datacount ------------>"+ datacount +" ,param ------------>" + param + " ,result ------------> " + json);
            return new ResponseEntity<String>(json,HttpStatus.OK);
        }
        try {
            for(JsonObject jsonObject : datas){
                //写原生json data
                writeDataService.getJsondata().put(jsonObject.toString());
                //写解析后的data
                writeDataService.getParsedata().put(getDataStr(jsonObject).toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            fail.put("errormsg","服务器内部错误");
            String json = new Gson().toJson(fail);
            logger.error("request failed:  datacount ------------>"+ datacount +" ,param ------------>" + param + " ,result ------------> " + json);
            return new ResponseEntity<String>(json,HttpStatus.OK);
        }
        success.put("errormsg","");
        String json = new Gson().toJson(success);
        long endTime = System.currentTimeMillis();
        //请求成功日志
        logger.info("request sucess: begintime = " + beginDateStr + ",datasize = " + datasize
                + ",datacount = " + datacount + ",processtime = " + (endTime - begintime) + "ms" + ",param = " + param);
        return new ResponseEntity<String>(json,HttpStatus.OK);
    }

    private StringBuffer getDataStr(JsonObject jsonObject) {
        StringBuffer stringBuffer = new StringBuffer();
        String delimiter = String.valueOf((char)5);
        String ID = jsonObject.get("id").getAsString();
        String byzqsyjb = "";
        String sector = jsonObject.get("sector").getAsString();
        String msgsubtype = jsonObject.get("msgsubtype").getAsString();
        String MTLM = "";
        if(LoadDataService.DATAS.containsKey(msgsubtype + "^^" + sector)){
            byzqsyjb = LoadDataService.DATAS.get(msgsubtype + "^^" + sector);
            MTLM = msgsubtype;
        }else{
            logger.info("data id = " + ID + ",ZLMC^^ZYLJ = " + msgsubtype + "^^" + sector + "在usrYBZXSJYB表中找不到");
            //如果找不到字段，更新缓存
            loadDataService.init();
            if(LoadDataService.DATAS.containsKey(msgsubtype + "^^" + sector)){
                byzqsyjb = LoadDataService.DATAS.get(msgsubtype + "^^" + sector);
                MTLM = msgsubtype;
            }else{
                //第二次依然没有找到，认为确实没有该字段，记录参数和日志，该条数据不写入大数据平台
                logger.error("data id = " + ID + ",ZLMC^^ZYLJ = " + msgsubtype + "^^" + sector + "在usrYBZXSJYB表中找不到:  datacount ------------>1 ,data ------------>" + jsonObject.toString());
                byzqsyjb = "^^^^^^"; //找不到暂时空处理
            }
        }
        String[] byzqsyjbSplits = byzqsyjb.split("\\^\\^");
        String XXFBRQ = jsonObject.get("pushdate").getAsString();
        String XXFBSJ = jsonObject.get("pushtime").getAsString();
        String MTCC = "";
        if(byzqsyjbSplits.length>0){
            if(LoadDataService.DMS.containsKey(byzqsyjbSplits[0])){
                MTCC = LoadDataService.DMS.get(byzqsyjbSplits[0]);
            }else{
                logger.info("data id = " + ID + ",DM = " + byzqsyjbSplits[0] + "在usrZXCLB表中找不到");
            }
        }
        String MTCCCL = byzqsyjbSplits.length == 0 ? "":byzqsyjbSplits[0];
        String SJYBM = byzqsyjbSplits.length<2 ? "":byzqsyjbSplits[1];
        String SJYBMMC = MTLM;
        String LMFL = byzqsyjbSplits.length<3 ? "":byzqsyjbSplits[2];
        String XWLY = MTCCCL;
        String XWBT = jsonObject.get("title").getAsString();
        String XWFBT = jsonObject.get("subtitle").getAsString();
        String XWZY = jsonObject.get("digester").getAsString();
        String XWNR = jsonObject.get("content").getAsString().
                replaceAll("hsjyp","").
                replaceAll(String.valueOf("\\\\u0003"),"").
                replaceAll(String.valueOf("\\\\u0007"),"").
                replaceAll("hsjytd","").
                replaceAll("hsjygtable","").
                replaceAll("hsjyth","").
                replaceAll("hsjygtbody","\\\\n").
                replaceAll("hsjygp","\\\\n");
        String ZXZZ = jsonObject.get("author").getAsString();
        String ZXJG = "";
        String ZXJGBH = "";
        String BMH = "";
        String BMXX = "";
        String LJDZ = jsonObject.get("weburl").getAsString();
        String XWNRHC = "";
        String XXJB = "";
        String QGZFM = jsonObject.get("rfflag").getAsString();
        String ZZS = jsonObject.get("zzs").getAsString();
        String HFS = jsonObject.get("hfs").getAsString();
        String DJS = jsonObject.get("djs").getAsString();
        String GKBZ = "";
        String XGRY = "";
        String XGRY2 = "";
        String XGSJ = "";
        String FBSJ = DateUtil.format(new Date(),DateUtil.format2);
        String SHRY = "";
        String JSID = "";
        String XWLYMC = MTLM;
        String msgtype = jsonObject.get("msgtype").getAsString();
        String rowkey = DateUtil.format(DateUtil.parse(FBSJ,DateUtil.format2),DateUtil.format3) + msgtype + UuidUtils.getUUID32();
        String host = jsonObject.get("host").getAsString();
        String msgsourceurl = jsonObject.get("msgsourceurl").getAsString();
        String contentimglinks = jsonObject.get("contentimglinks").getAsString();
        String contentimglinkslocations = "";
        String infotype = "1"; //1:博越，2：宏观新闻，3：证监会行业新闻
        String infoflag = "";

        stringBuffer.append(rowkey).append(delimiter).append(ID).append(delimiter).append(XXFBRQ).append(delimiter).append(XXFBSJ).append(delimiter).
                append(MTCC).append(delimiter).append(MTCCCL).append(delimiter).append(XWLY).append(delimiter).append(XWLYMC).append(delimiter).
                append(LMFL).append(delimiter).append(MTLM).append(delimiter).append(XWBT).append(delimiter)
                .append(XWFBT).append(delimiter).append(XWZY).append(delimiter).append(XWNR).append(delimiter)
                .append(ZXZZ).append(delimiter).append(ZXJG).append(delimiter).append(ZXJGBH).append(delimiter).
                append(BMH).append(delimiter).append(BMXX).append(delimiter).append(LJDZ).append(delimiter).
                append(XWNRHC).append(delimiter).append(XXJB).append(delimiter).append(SJYBM).append(delimiter).append(SJYBMMC).append(delimiter).
                append(QGZFM).append(delimiter).append(ZZS).append(delimiter).append(HFS).append(delimiter).
                append(DJS).append(delimiter).append(GKBZ).append(delimiter).append(XGRY).append(delimiter).
                append(XGRY2).append(delimiter).append(XGSJ).append(delimiter).append(FBSJ).append(delimiter).
                append(SHRY).append(delimiter).append(JSID).append(delimiter).append(host).append(delimiter).
                append(msgsourceurl).append(delimiter).append(msgtype).append(delimiter).append(msgsubtype).append(delimiter).
                append(contentimglinks).append(delimiter).append(contentimglinkslocations).append(delimiter).append(sector).append(delimiter).
                append(infotype).append(delimiter).append(infoflag);
        return stringBuffer;
    }


    //测试环境写入
    //字段转换
//            for(JsonObject jsonObject : datas){
//                datacount += 1;
//                fw.write(jsonObject.toString().replaceAll("hsjyp","").  //将<p>直接去除
//                        replaceAll("hsjygp","\n").  //将</p>转换成换行
//                        replaceAll(String.valueOf("\\\\u0001"),"\n").  //将<table>转换成换行
//                        replaceAll(String.valueOf("\\\\u0003"),"").  //将<tbody>直接去除
//                        replaceAll(String.valueOf("\\\\u0007"),"").  //将<tr>直接去除
//                        replaceAll(String.valueOf("\\\\u0006"),"\n").  //将</tr>转换成换行
//                        replaceAll("hsjygtd","\t").  //将<\td>转换成回车符
//                        replaceAll("hsjytd","").   //将<td>直接去除
//                        replaceAll("hsjygtbody","\n").  //将</tbody>换行处理
//                        replaceAll("hsjygtable","").  //将<table>直接去除
//                        replaceAll("hsjyth","").  //将<th>直接去除
//                        replaceAll("hsjygth","\t") + "\n");  //将<\th>换成回车符
//            }


    public List<JsonObject> parseParam(JsonObject object){
        Set<String> keys = object.keySet();
        //判断是否缺少字段
        for(Object key : paramSet){
            if(!keys.contains(key.toString())){
                throw new InvalidRequestException( key.toString() + " 字段不存在");
            }
        }

        TreeMap<String,String> sig = new TreeMap<>();
        sig.put("secretkey", SignatureUtil.secretKey);
        String tag = object.get("tag").getAsString();
        if("".equals(tag.trim())){
            throw new InvalidRequestException("tag 字段不能为空");
        }else if(!"gildata".equals(tag)){
            throw new InvalidRequestException("tag 字段不符合规范");
        }
        String timestamp = object.get("timestamp").getAsString();
        if("".equals(timestamp.trim())){
            throw new InvalidRequestException("timestamp 字段不能为空");
        }else if(!isValidDate(timestamp,"yyyy-MM-dd HH:mm:ss")){
            throw new InvalidRequestException("timestamp 字段不符合规范");
        }
        sig.put("timestamp",timestamp);
        String sign = object.get("sign").getAsString();
        if("".equals(sign.trim())){
            throw new InvalidRequestException("sign 字段不能为空");
        }
        String datasize = object.get("datasize").getAsString();
        if("".equals(datasize.trim())){
            throw new InvalidRequestException("datasize 字段不能为空");
        }
        sig.put("datasize",datasize);
        String datatype = object.get("datatype").getAsString();
        if("".equals(datatype.trim())){
            throw new InvalidRequestException("datatype 字段不能为空");
        }else if(!"boyue".equals(datatype)){
            throw new InvalidRequestException("datatype 字段不符合规范");
        }
        sig.put("datatype",datatype);
        JsonArray datas = object.get("data").getAsJsonArray();
        if(datas.size() == 0){
            throw new InvalidRequestException("data 字段不能为空");
        }

        sig.put("data",datas.toString());
        String serverSign = "";
        try {
            serverSign = SignatureUtil.getSignStr(sig);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidRequestException("请求参数有问题，签名计算失败");
        }
        if(!serverSign.equals(sign)){
            throw new UnauthorizedException("签名校验不正确，权限不足");
        }
        List<JsonObject> dataList = new ArrayList<>();
        for(int i=0;i<datas.size();i++){
            JsonObject data = datas.get(i).getAsJsonObject();
            dataCheck(data);
            dataList.add(data);
        }
        return dataList;
    }


    public void dataCheck(JsonObject object){
        Set<String> keys = object.keySet();
        for(Object key : dataSet){
            if(!keys.contains(key.toString())){
                throw new InvalidRequestException( key.toString() + " 字段不存在");
            }
        }
        String id = object.get("id").getAsString();
        if("".equals(id.trim())){
            throw new InvalidRequestException("id 字段不能为空");
        }else if(id.length()>50){
            throw new InvalidRequestException("id 字段长度超过限制");
        }

        String weburl = object.get("weburl").getAsString();
        if("".equals(weburl.trim())){
            throw new InvalidRequestException("weburl 字段不能为空");
        }else if(weburl.length()>300){
            throw new InvalidRequestException("weburl 字段长度超过限制");
        }

        String sector = object.get("sector").getAsString();
        if("".equals(sector.trim())){
            throw new InvalidRequestException("sector 字段不能为空");
        }else if(sector.length()>300){
            throw new InvalidRequestException("sector 字段长度超过限制");
        }

        String msgsubtype = object.get("msgsubtype").getAsString();
        if("".equals(msgsubtype.trim())){
            throw new InvalidRequestException("msgsubtype 字段不能为空");
        }

        String msgsourceurl = object.get("msgsourceurl").getAsString();
        if("".equals(msgsourceurl.trim())){
            throw new InvalidRequestException("msgsourceurl 字段不能为空");
        }else if(msgsourceurl.length()>400){
            throw new InvalidRequestException("msgsourceurl 字段长度超过限制");
        }

        String title = object.get("title").getAsString();
        if("".equals(title.trim())){
            throw new InvalidRequestException("title 字段不能为空");
        }else if(title.length()>200){
            throw new InvalidRequestException("title 字段长度超过限制");
        }

        String subtitle = object.get("subtitle").getAsString();
        if(subtitle.length()>200){
            throw new InvalidRequestException("subtitle 字段长度超过限制");
        }

        String content = object.get("content").getAsString();
        if("".equals(content.trim())){
            throw new InvalidRequestException("content 字段不能为空");
        }

        String digester = object.get("digester").getAsString();
        if(digester.length()>2000){
            throw new InvalidRequestException("digester 字段长度超过限制");
        }

        String pushdate = object.get("pushdate").getAsString();
        if("".equals(pushdate.trim())){
            throw new InvalidRequestException("pushdate 字段不能为空");
        }else if(!isValidDate(pushdate,"yyyy-MM-dd")){
            throw new InvalidRequestException("pushdate 字段不符合规范");
        }

        String pushtime = object.get("pushtime").getAsString();
        if(!"".equals(pushtime.trim())&&!isValidDate(pushtime,"HH:mm:ss")){
            throw new InvalidRequestException("pushtime 字段不符合规范");
        }

        String address_s = object.get("address_s").getAsString();
        if(address_s.length()>100){
            throw new InvalidRequestException("address_s 字段长度超过限制");
        }

        String sourceurl = object.get("sourceurl").getAsString();
        if(sourceurl.length()>200){
            throw new InvalidRequestException("sourceurl 字段长度超过限制");
        }

        String quote_author = object.get("quote_author").getAsString();
        if(quote_author.length()>500){
            throw new InvalidRequestException("quote_author 字段长度超过限制");
        }

        String quote_content = object.get("quote_content").getAsString();
        if(quote_content.length()>4000){
            throw new InvalidRequestException("quote_content 字段长度超过限制");
        }

        String quote_date = object.get("quote_date").getAsString();
        if(quote_date.length()>20){
            throw new InvalidRequestException("quote_date 字段长度超过限制");
        }
    }


    public boolean isValidDate(String str,String strPaetrn){
        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat(strPaetrn);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }

}
