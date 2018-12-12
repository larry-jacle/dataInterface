package com.gildata.byinterserver.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by LiChao on 2018/11/20
 */

@Configuration
public class JDBCConfig {

    @Bean(name = "jypConfigDataSource")
    @Qualifier("jypConfigDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.jypconfig")
    public DataSource jypConfigDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jypConfigJdbcTemplate")
    public JdbcTemplate jypConfigJdbcTemplate(@Qualifier("jypConfigDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
