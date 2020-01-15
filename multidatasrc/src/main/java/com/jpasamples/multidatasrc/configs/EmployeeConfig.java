package com.jpasamples.multidatasrc.configs;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
Create:
1. DataSource details
2. EntityManagerFactory
3. TransactionManager
*/

//ref:https://www.javadevjournal.com/spring-boot/multiple-data-sources-with-spring-boot/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "employeeEntityManagerFactory",
 transactionManagerRef = "employeeTransactionManager", basePackages = {"com.jpasamples.multidatasrc.dba" })
public class EmployeeConfig {
    @Primary
    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("employeeDataSource") DataSource dataSource) {

        return builder.dataSource(dataSource)
        .packages("com.jpasamples.multidatasrc.dba") //The names of packages to scan for @Entity annotations.
        //The name of the persistence unit. If only building one EntityManagerFactory you can omit this, 
        //but if there are more than one in the same application you should give them distinct names.
        .persistenceUnit("db1")
                .build();
    }

    @Primary
    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager empTransactionManager(
            @Qualifier("employeeEntityManagerFactory") EntityManagerFactory empEntityManagerFactory) {
        return new JpaTransactionManager(empEntityManagerFactory);
    }
}