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

//ref:https://www.javadevjournal.com/spring-boot/multiple-data-sources-with-spring-boot/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "employeeBEntityManagerFactory",
 transactionManagerRef = "employeeBTransactionManager", basePackages = {"com.jpasamples.multidatasrc.dbb" })
public class EmployeeBConfig {
  //  @Primary
    @Bean(name = "employeeBDataSource")
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().build();
    }

  //  @Primary
    @Bean(name = "employeeBEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("employeeBDataSource") DataSource dataSource) {

        return builder.dataSource(dataSource)
        .packages("com.jpasamples.multidatasrc.dbb") //The names of packages to scan for @Entity annotations.
        //The name of the persistence unit. If only building one EntityManagerFactory you can omit this, 
        //but if there are more than one in the same application you should give them distinct names.
        .persistenceUnit("db2")
                .build();
    }

   // @Primary
    @Bean(name = "employeeBTransactionManager")
    public PlatformTransactionManager empTransactionManager(
            @Qualifier("employeeBEntityManagerFactory") EntityManagerFactory empEntityManagerFactory) {
        return new JpaTransactionManager(empEntityManagerFactory);
    }
}