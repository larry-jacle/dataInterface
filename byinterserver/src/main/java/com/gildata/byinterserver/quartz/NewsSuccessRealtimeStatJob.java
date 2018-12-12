package com.gildata.byinterserver.quartz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gildata.byinterserver.constant.Constant;
import com.gildata.byinterserver.domain.Logrecord;
import com.gildata.byinterserver.domain.UsrBYZXSLTJ;
import com.gildata.byinterserver.domain.UsrBYZXXQ;
import com.gildata.byinterserver.service.*;
import com.gildata.byinterserver.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenchen on 2018/11/27.
 */
public class NewsSuccessRealtimeStatJob {
    private final Logger logger = LoggerFactory.getLogger(NewsSuccessRealtimeStatJob.class);

    private UsrBYZXSLTJService usrBYZXSLTJService;

    private UsrBYZXXQService usrBYZXXQService;

    private LogrecordService logrecordService;

    private Logrecord logRecord = null;

    private static volatile int flag = 0;

    public void doSuccessJob() throws IOException {
        synchronized (NewsSuccessRealtimeStatJob.class) {
            if (flag == 1) {
                logger.debug("success log job has run");
                return;
            }
            flag = 1;
        }

        usrBYZXSLTJService = StaticAppContext.getContext().getBean(UsrBYZXSLTJServiceImpl.class);
        usrBYZXXQService = StaticAppContext.getContext().getBean(UsrBYZXXQServiceImpl.class);
        logrecordService = StaticAppContext.getContext().getBean(LogrecordServiceImpl.class);

        logRecord = logrecordService.findAll().get(1);
//
        String logDir = Init.getProperty().getProperty(Constant.LOG_DIR);
        File successFile = new File(logDir, Init.getProperty().getProperty(Constant.SUCCESS_FILE_NAME));
        saveMsg(successFile);

        flag = 0;
    }

    public void saveMsg(File file) throws IOException {
        if (!file.exists()) {
            logger.warn("file [{}] is not exist!", file.getAbsolutePath());
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<UsrBYZXSLTJ> usrBYZXSLTJList = new ArrayList<>();
        List<UsrBYZXXQ> usrBYZXXQList = new ArrayList<>();

        int lineCount = 0;
        int successOffSet = logRecord.getIndexloc();
        String line;
        while ((line = reader.readLine()) != null) {
            // 从上次job结束时读取位置的下一行开始读取
            if (lineCount++ < successOffSet) continue;

            String successInfo = line.substring(line.indexOf("param = ") + 8);
            JSONObject json = null;
            try {
                json = JSONObject.parseObject(successInfo);
            } catch (Exception e) {
                continue;
            }
            Date timestamp = DateUtil.parse(json.getString("timestamp"), DateUtil.format2);
//            int datasize = json.getIntValue("datasize");
            JSONArray data = json.getJSONArray("data");
            int datasize = data.size();

            UsrBYZXSLTJ usrBYZXSLTJ = new UsrBYZXSLTJ();
            usrBYZXSLTJ.setTimestamp(timestamp);
            usrBYZXSLTJ.setCount(datasize);
            usrBYZXSLTJ.setType(Constant.LOG_TYPE_SUCCESS);
            usrBYZXSLTJList.add(usrBYZXSLTJ);

            for (int i = 0; i < datasize; i++) {
                JSONObject msg = data.getJSONObject(i);
                UsrBYZXXQ usrBYZXXQ = new UsrBYZXXQ();
                usrBYZXXQ.setTimestamp(timestamp);
                usrBYZXXQ.setInserttime(new Date());
                usrBYZXXQ.setMsgtype(msg.getInteger("msgtype"));
                usrBYZXXQ.setHost(msg.getString("host"));
                usrBYZXXQ.setMsgsourceurl(msg.getString("msgsourceurl"));
                usrBYZXXQ.setWeburl(msg.getString("weburl"));
                usrBYZXXQList.add(usrBYZXXQ);
            }
        }
        reader.close();

        if (lineCount < successOffSet) {
            logRecord.setIndexloc(0);
        } else {
            logRecord.setIndexloc(lineCount);
        }

        logrecordService.save(logRecord);

        usrBYZXSLTJService.save(usrBYZXSLTJList);
        usrBYZXXQService.save(usrBYZXXQList);
    }
}
