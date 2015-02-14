package com.catalog.repository.dao;

import com.catalog.repository.dao.exception.DaoException;
import com.catalog.repository.domain.ApplicationUser;

import java.util.List;

public interface ApplicationUserDao {

    public void addApplicationUser(ApplicationUser user) throws DaoException;

    public void deleteApplicationUser(Integer id);

    public void updateApplicationUser(ApplicationUser user) throws DaoException;

    public ApplicationUser getApplicationUser(Integer id);

    public ApplicationUser getApplicationUserByEmailAddress(String userName);

    List<ApplicationUser> getApplicationUsers();
}
