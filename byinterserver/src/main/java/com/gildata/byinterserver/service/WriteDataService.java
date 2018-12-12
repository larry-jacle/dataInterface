package com.gildata.byinterserver.service;

import com.gildata.byinterserver.exception.NotFoundException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by LiChao on 2018/11/27
 */

@Service
public class WriteDataService {

    @Value("${log.bydata.path}")
    private String bydata;

    @Value("${log.loaddata.path}")
    private String loaddata;

    @Value("${log.bydata.length}")
    private Long bydataLen;

    @Value("${log.loaddata.length}")
    private Long loaddataLen;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //队列容量设置为1000，可以最大缓存1000条咨询信息
    private  BlockingQueue<String> parsedata = new LinkedBlockingQueue<String>(1000);

    //队列容量设置为500，可以最大缓存500条参数信息
    private  BlockingQueue<String> jsondata = new LinkedBlockingQueue<String>(500);


    private JsonParser parser=new JsonParser();

    private String filenamePrefix = "";

    //"bydata/" 存放json数据
    //"loaddata/" 用于加载到大数据平台
    //"E:\IDG\bydata\" 本地环境
    private FileWriter fw = null;  //写json data

    private FileWriter fw2 = null; //写parsedata


    public void init(){
        JsonDataConsumer jsonDataConsumer = new JsonDataConsumer();
        ParseDataConumer parseDataConumer = new ParseDataConumer();
        Thread jsonDataT = new Thread(jsonDataConsumer);
        Thread parseDataT = new Thread(parseDataConumer);
        jsonDataT.setDaemon(true);
        parseDataT.setDaemon(true);
        jsonDataT.start();
        parseDataT.start();
    }

    //写原生json data
    class JsonDataConsumer implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
//                    changeFileName();
                    filenamePrefix = getFilenamePrefix();
                    fw = getFw(bydata,filenamePrefix,bydataLen);
                    String param = jsondata.take();
                    fw.write(param + "\n");
                    fw.close();
                    JsonObject object = (JsonObject) parser.parse(param);
                    String id = object.get("id").getAsString();
                    String timestamp = object.get("timestamp").getAsString();
                    //写入成功记录
                    logger.info("write success: id = " + id + ", timestamp = " + timestamp);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //写解析后的信息
    class ParseDataConumer implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
//                    changeFileName();
                    filenamePrefix = getFilenamePrefix();
                    fw2 = getFw(loaddata,filenamePrefix,loaddataLen);
                    String data = parsedata.take();
                    fw2.write(data + "\n");
                    fw2.close();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void changeFileName() throws IOException {
        //写入之前先判断日期是否发生改变
        synchronized (this){
            if(!filenamePrefix.equals(getFilenamePrefix())){
                filenamePrefix = getFilenamePrefix();
                fw = getFw(bydata,filenamePrefix,bydataLen);
                fw2 = getFw(loaddata,filenamePrefix,loaddataLen);
            }
        }
    }


    public FileWriter getFw(String filePath,String filename,long length) throws IOException,NotFoundException {
        int count = getFileCount(filePath,filename);
        File file = new File(filePath+ filename +".txt");
        FileWriter fw = null;
        if(file.length()>length){
            //文件大小超过128M，保存为新的文件
            file.renameTo(new File(filePath + filename + "_" + count +".txt"));
        }
        fw  = new FileWriter(filePath + filename +".txt",true);
        return fw;
    }


    public int getFileCount(String filePath,String filename){
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        File[] filelist = file.listFiles();
        int count = 0;
        for(int i=0;i<filelist.length;i++){
            if (filelist[i].isFile()) {
                if(filelist[i].getName().contains(filename)){
                    count += 1;
                }
            }
        }
        //文件名从1开始
        return count;
    }


    private String getFilenamePrefix() {
        String filename = "";
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        filename=sdf.format(dt);
        return filename;
    }


    public BlockingQueue<String> getParsedata() {
        return parsedata;
    }

    public void setParsedata(BlockingQueue<String> parsedata) {
        this.parsedata = parsedata;
    }

    public BlockingQueue<String> getJsondata() {
        return jsondata;
    }

    public void setJsondata(BlockingQueue<String> jsondata) {
        this.jsondata = jsondata;
    }
}
