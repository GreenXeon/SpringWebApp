package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.epam.esm")
public class RepositoryConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        HikariConfig databaseConfig = new HikariConfig();
        //DriverManagerDataSource dataSource = new DriverManagerDataSource();
        databaseConfig.setDriverClassName(environment.getRequiredProperty("db.driver"));
        databaseConfig.setJdbcUrl(environment.getRequiredProperty("db.url"));
        databaseConfig.setUsername(environment.getRequiredProperty("db.username"));
        databaseConfig.setPassword(environment.getRequiredProperty("db.password"));
        databaseConfig.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty("db.maxPoolSize")));
        return new HikariDataSource(databaseConfig);
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
