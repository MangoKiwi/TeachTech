package com.mangokiwi.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by zhenfeng on 4/24/17.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.mangokiwi.repository")
@PropertySource("application.properties")
public class DataConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("TeachTech.db.driver"));
        dataSource.setUrl(environment.getProperty("TeachTech.db.url"));
        dataSource.setUsername(environment.getProperty("TeachTech.db.username"));
        dataSource.setPassword(environment.getProperty("TeachTech.db.password"));
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(){
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("resources/migrations/");
        flyway.setDataSource(dataSource());
        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(environment.getProperty("ModelPackage"));
        factory.setJpaProperties(getHibernateProperties());

        return factory;
    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.hibernate.implicit_naming_strategy", environment.getProperty("hibernate.hibernate.implicit_naming_strategy"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}
