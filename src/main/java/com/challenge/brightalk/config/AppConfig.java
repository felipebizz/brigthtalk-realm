package com.challenge.brightalk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;
import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("com.challenge.brightalk.dao"),
        @ComponentScan("com.challenge.brightalk.service"),
        @ComponentScan("com.challenge.brightalk.mapper")})
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();
        // Setting JDBC properties
        props.put(DRIVER, Objects.requireNonNull(env.getProperty("mysql.driver")));
        props.put(URL, Objects.requireNonNull(env.getProperty("mysql.url")));
        props.put(USER, Objects.requireNonNull(env.getProperty("mysql.user")));
        props.put(PASS, Objects.requireNonNull(env.getProperty("mysql.password")));

        // Setting Hibernate properties
        props.put(SHOW_SQL, Objects.requireNonNull(env.getProperty("hibernate.show_sql")));
        props.put(HBM2DDL_AUTO, Objects.requireNonNull(env.getProperty("hibernate.hbm2ddl.auto")));

        // Setting C3P0 properties
        props.put(C3P0_MIN_SIZE, Objects.requireNonNull(env.getProperty("hibernate.c3p0.min_size")));
        props.put(C3P0_MAX_SIZE, Objects.requireNonNull(env.getProperty("hibernate.c3p0.max_size")));
        props.put(C3P0_ACQUIRE_INCREMENT, Objects.requireNonNull(env.getProperty("hibernate.c3p0.acquire_increment")));
        props.put(C3P0_TIMEOUT, Objects.requireNonNull(env.getProperty("hibernate.c3p0.timeout")));
        props.put(C3P0_MAX_STATEMENTS, Objects.requireNonNull(env.getProperty("hibernate.c3p0.max_statements")));

        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("com.challenge.brightalk.model");

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
