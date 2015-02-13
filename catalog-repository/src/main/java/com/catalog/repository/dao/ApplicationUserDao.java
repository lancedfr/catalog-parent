package com.catalog.repository.dao;

import com.catalog.repository.domain.ApplicationUser;

import java.util.List;

public interface ApplicationUserDao {

    public void addApplicationUser(ApplicationUser user);

    public void deleteApplicationUser(Integer id);

    public void updateApplicationUser(ApplicationUser user);

    public ApplicationUser getApplicationUser(Integer id);

    public ApplicationUser getApplicationUserByUserName(String userName);

    List<ApplicationUser> getApplicationUsers();
}
