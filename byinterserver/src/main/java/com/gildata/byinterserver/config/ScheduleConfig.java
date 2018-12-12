package com.gildata.byinterserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by LiChao on 2018/11/20
 */
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean strategyScheduler() {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setSchedulerName("strategyScheduler");

        schedulerFactory.setAutoStartup(false);
        return schedulerFactory;
    }

}
