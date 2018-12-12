package com.gildata.byinterserver;

import com.gildata.byinterserver.quartz.Init;
import com.gildata.byinterserver.quartz.StaticAppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ByinterserverApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ByinterserverApplication.class, args);
		StaticAppContext.setContext(context);
		Init.init();
	}
}
