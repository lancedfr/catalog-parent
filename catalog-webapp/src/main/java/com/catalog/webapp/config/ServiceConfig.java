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

package com.catalog.webapp.config;

import com.catalog.repository.dao.ApplicationUserDao;
import com.catalog.service.applicationuser.ApplicationUserService;
import com.catalog.service.applicationuser.ApplicationUserServiceImpl;
import com.catalog.spring.config.AbstractSpringConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ServiceConfig for all spring services
 * Created by Lance on 22/02/2015.
 */
@Configuration
class ServiceConfig extends AbstractSpringConfiguration {

    @Bean
    public ApplicationUserService applicationUserService() {
        ApplicationUserServiceImpl applicationUserService = new ApplicationUserServiceImpl();
        applicationUserService.setApplicationUserDao(getBean(ApplicationUserDao.class));
        return applicationUserService;
    }
}
