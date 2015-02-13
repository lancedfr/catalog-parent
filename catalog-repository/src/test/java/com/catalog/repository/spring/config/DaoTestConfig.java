package com.catalog.repository.spring.config;

import com.catalog.repository.dao.ApplicationUserDao;
import com.catalog.repository.dao.ApplicationUserDaoImpl;
import com.catalog.repository.dao.ArticleDao;
import com.catalog.repository.dao.ArticleDaoImpl;
import com.catalog.spring.config.AbstractSpringConfiguration;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Lance on 11/02/2015.
 */
@Configuration
public class DaoTestConfig extends AbstractSpringConfiguration {

    @Bean
    public ApplicationUserDao productDao() {
        ApplicationUserDaoImpl applicationUserDao = new ApplicationUserDaoImpl();
        applicationUserDao.setSessionFactory(getBean(SessionFactory.class));
        return applicationUserDao;
    }

    @Bean
    public ArticleDao articleDao() {
        ArticleDaoImpl articleDao = new ArticleDaoImpl();
        articleDao.setSessionFactory(getBean(SessionFactory.class));
        return articleDao;
    }
}
