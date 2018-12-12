package com.gildata.byinterserver.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 调度任务
 * Created by chenchen on 2018/11/16.
 */
@Component
public class QuartzSchedule {
    private final Logger logger = LoggerFactory.getLogger(QuartzSchedule.class);

    @Value("${task.switch}")
    private String taskSwitch;

    /**
     * 注：打包时该块代码取消注释
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void realtimeSuccessWork() throws Exception {
        if ("close".equals(taskSwitch)) {
            return;
        }

        Date begin = new Date();
        logger.debug("<<<<<<<<开始执行资讯成功日志准实时统计任务");

        new NewsSuccessRealtimeStatJob().doSuccessJob();

        logger.debug("本次资讯成功日志准实时统计任务执行完成>>>>>>>>");
        Date end = new Date();
        logger.debug("this job costs {} ms", end.getTime() - begin.getTime());
    }

    /**
     * 注：打包时该块代码取消注释
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void realtimeFailWork() throws Exception {
        if ("close".equals(taskSwitch)) {
            return;
        }

        Date begin = new Date();
        logger.debug("<<<<<<<<开始执行资讯失败日志准实时统计任务");

        new NewsFailRealtimeStatJob().doFailJob();

        logger.debug("本次资讯失败日志准实时统计任务执行完成>>>>>>>>");
        Date end = new Date();
        logger.debug("this job costs {} ms", end.getTime() - begin.getTime());
    }
}
