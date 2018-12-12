package com.gildata.byinterserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiChao on 2018/11/8
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogDemo {

    private final Logger log = LoggerFactory.getLogger(LogDemo.class);


    @Test
    public void test(){
        log.debug("hello world!");
        log.error("error hello world!");
        log.info("info hello world!");
    }

}
