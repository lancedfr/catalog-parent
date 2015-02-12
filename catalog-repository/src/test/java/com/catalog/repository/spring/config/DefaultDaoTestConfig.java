package com.catalog.repository.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Lance on 11/02/2015.
 */
@Configuration
@Import({PersistenceTestConfig.class, DaoTestConfig.class})
public class DefaultDaoTestConfig {
}
