package com.catalog.repository.dao;

import com.catalog.repository.domain.ApplicationUser;
import com.catalog.repository.spring.config.DefaultDaoTestConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lance on 11/02/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DefaultDaoTestConfig.class})
public class ApplicationUserDaoImplIT {

    @Autowired
    private ApplicationContext context;
    private ApplicationUserDao applicationUserDao;

    @Before
    public void before() {
        applicationUserDao = context.getBean(ApplicationUserDao.class);
    }

    public ApplicationUser getTestApplicationUser() {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUserName(RandomStringUtils.random(1));
        applicationUser.setFirstName("FirstName");
        applicationUser.setGender("Gender");
        applicationUser.setAge(1);
        applicationUser.setLastName("LastName");
        applicationUser.setPassword("Password");
        return applicationUser;
    }

    @Test
    public void testGetApplicationUser() throws Exception {
        ApplicationUser applicationUser = getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        ApplicationUser getUser = applicationUserDao.getApplicationUser(applicationUser.getId());
        assertEquals(applicationUser.getFirstName(), getUser.getFirstName());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

    @Test
    public void testUpdateApplicationUser() throws Exception {
        ApplicationUser applicationUser = getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        ApplicationUser getUser1 = applicationUserDao.getApplicationUser(applicationUser.getId());
        getUser1.setFirstName("Other");
        applicationUserDao.updateApplicationUser(getUser1);
        ApplicationUser getUser2 = applicationUserDao.getApplicationUser(getUser1.getId());
        assertEquals("Other", getUser2.getFirstName());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

    @Test
    public void testAddApplicationUser() {
        ApplicationUser applicationUser = getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        List<ApplicationUser> applicationUsers = applicationUserDao.getApplicationUsers();
        assertEquals(1, applicationUsers.size());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

    @Test
    public void testDeleteApplicationUser() throws Exception {
        ApplicationUser applicationUser = getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
        List<ApplicationUser> applicationUsers = applicationUserDao.getApplicationUsers();
        assertEquals(0, applicationUsers.size());
    }

    @Test
    public void testGetApplicationUserByUserName() throws Exception {
        ApplicationUser applicationUser = getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        List<ApplicationUser> applicationUsers = applicationUserDao.getApplicationUsers();
        assertEquals(1, applicationUsers.size());
        ApplicationUser userByUserName = applicationUserDao.getApplicationUserByUserName(applicationUser.getUserName());
        assertEquals(userByUserName.getFirstName(), applicationUser.getFirstName());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

}
