/*
 * Copyright (c) 2015. All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the Apache License, Version 2.0 (the "License")
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * You should have received a copy of the "License" with this file.
 * If not, please obtain a copy here http://www.apache.org/licenses/LICENSE-2.0
 */

package com.catalog.repository.spring.config;

import com.catalog.spring.config.AbstractSpringConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Lance on 11/02/2015.
 */
@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence-test.properties"})
public class PersistenceTestConfig extends AbstractSpringConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getApplicationContext().getEnvironment().getProperty("jdbc.driverClassName"));
        dataSource.setUrl(getApplicationContext().getEnvironment().getProperty("jdbc.url"));
        dataSource.setUsername(getApplicationContext().getEnvironment().getProperty("jdbc.username"));
        dataSource.setPassword(getApplicationContext().getEnvironment().getProperty("jdbc.password"));
        dataSource.setPassword("1");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getBean(DataSource.class));
        sessionFactory.setPackagesToScan("com.catalog.repository.domain");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        LocalSessionFactoryBean localSessionFactoryBean = getBean(LocalSessionFactoryBean.class);
        hibernateTransactionManager.setSessionFactory(localSessionFactoryBean.getObject());
        return hibernateTransactionManager;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", getApplicationContext().getEnvironment().getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.dialect", getApplicationContext().getEnvironment().getProperty("hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
                setProperty("hibernate.generate_statistics", "true");
            }
        };
    }
}
