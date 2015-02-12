package com.catalog.service;

import com.catalog.repository.dao.ApplicationUserDao;
import com.catalog.repository.domain.ApplicationUser;

/**
 * Created by Lance on 12/02/2015.
 */
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private ApplicationUserDao applicationUserDao;

    @Override
    public ApplicationUser getApplicationUser(Integer id) {
        return applicationUserDao.getApplicationUser(id);
    }

    public void setApplicationUserDao(ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    public ApplicationUserDao getApplicationUserDao() {
        return applicationUserDao;
    }
}