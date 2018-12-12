package com.gildata.byinterserver.quartz;

import com.gildata.byinterserver.constant.Constant;
import com.gildata.byinterserver.domain.Logrecord;
import com.gildata.byinterserver.domain.UsrBYZXSLTJ;
import com.gildata.byinterserver.service.LogrecordService;
import com.gildata.byinterserver.service.LogrecordServiceImpl;
import com.gildata.byinterserver.service.UsrBYZXSLTJService;
import com.gildata.byinterserver.service.UsrBYZXSLTJServiceImpl;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenchen on 2018/11/27.
 */
public class NewsFailRealtimeStatJob {
    private final Logger logger = LoggerFactory.getLogger(NewsFailRealtimeStatJob.class);

    private UsrBYZXSLTJService usrBYZXSLTJService;

    private LogrecordService logrecordService;

    private Logrecord logRecord = null;

    private Pattern failPattern = Pattern.compile("\"timestamp\":\"(\\d{4}\\-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\"[\\S]+\"datasize\":\"([\\d]+)\"");

    private static volatile int flag = 0;

    public void doFailJob() throws IOException {
        synchronized (NewsFailRealtimeStatJob.class) {
            if (flag == 1) {
                logger.debug("fail log job has run");
                return;
            }
            flag = 1;
        }
        usrBYZXSLTJService = StaticAppContext.getContext().getBean(UsrBYZXSLTJServiceImpl.class);
        logrecordService = StaticAppContext.getContext().getBean(LogrecordServiceImpl.class);

        logRecord = logrecordService.findAll().get(0);

        String logDir = Init.getProperty().getProperty(Constant.LOG_DIR);
        File failFile = new File(logDir, Init.getProperty().getProperty(Constant.FAIL_FILE_NAME));
        saveMsg(failFile);

        flag = 0;
    }

    public void saveMsg(File file) throws IOException {
        if (!file.exists()) {
            logger.warn("file [{}] is not exist!", file.getAbsolutePath());
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<UsrBYZXSLTJ> usrBYZXSLTJList = new ArrayList<>();

        int lineCount = 0;
        int successOffSet = logRecord.getIndexloc();
        String line;
        while ((line = reader.readLine()) != null) {
            // 从上次job结束时读取位置的下一行开始读取
            if (lineCount++ < successOffSet) continue;

            Matcher matcher = failPattern.matcher(line);
            if (matcher.find()) {
                Date timestamp = DateUtil.parse(matcher.group(1), DateUtil.format2);
                int datasize = Integer.parseInt(matcher.group(2));

                UsrBYZXSLTJ usrBYZXSLTJ = new UsrBYZXSLTJ();
                usrBYZXSLTJ.setTimestamp(timestamp);
                usrBYZXSLTJ.setCount(datasize);
                usrBYZXSLTJ.setType(Constant.LOG_TYPE_FAIL);
                usrBYZXSLTJList.add(usrBYZXSLTJ);
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
    }
}
