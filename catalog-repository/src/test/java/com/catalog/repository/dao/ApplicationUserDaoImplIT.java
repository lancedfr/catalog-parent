package com.catalog.repository.dao;

import com.catalog.repository.dao.exception.DaoException;
import com.catalog.repository.domain.ApplicationUser;
import com.catalog.repository.spring.config.DefaultDaoTestConfig;
import com.catalog.repository.util.DomainUtil;
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

    @Test
    public void testAddApplicationUser() throws Exception {
        ApplicationUser applicationUser = DomainUtil.getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        List<ApplicationUser> applicationUsers = applicationUserDao.getApplicationUsers();
        assertEquals(1, applicationUsers.size());
        applicationUserDao.addApplicationUser(applicationUser);
        applicationUsers = applicationUserDao.getApplicationUsers();
        assertEquals(1, applicationUsers.size());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

    @Test
    public void testDeleteApplicationUser() throws Exception {
        ApplicationUser applicationUser = DomainUtil.getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
        List<ApplicationUser> applicationUsers = applicationUserDao.getApplicationUsers();
        assertEquals(0, applicationUsers.size());
    }

    @Test
    public void testUpdateApplicationUser() throws Exception {
        ApplicationUser applicationUser = DomainUtil.getTestApplicationUser();
        applicationUser.setId(Integer.MAX_VALUE);
        applicationUserDao.addApplicationUser(applicationUser);
        ApplicationUser addedApplicationUser = applicationUserDao.getApplicationUser(applicationUser.getId());
        addedApplicationUser.setFirstName("Other");
        applicationUserDao.updateApplicationUser(addedApplicationUser);
        ApplicationUser updatedApplicationUser = applicationUserDao.getApplicationUser(addedApplicationUser.getId());
        assertEquals("Other", updatedApplicationUser.getFirstName());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

    @Test(expected = DaoException.class)
    public void testUpdateApplicationUserDaoException() throws Exception {
        ApplicationUser applicationUser = DomainUtil.getTestApplicationUser();
        applicationUser.setId(Integer.valueOf(RandomStringUtils.randomNumeric(4)));
        applicationUserDao.updateApplicationUser(applicationUser);
    }

    @Test
    public void testGetApplicationUser() throws Exception {
        ApplicationUser applicationUser = DomainUtil.getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        ApplicationUser getUser = applicationUserDao.getApplicationUser(applicationUser.getId());
        assertEquals(applicationUser.getFirstName(), getUser.getFirstName());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

    @Test
    public void testGetApplicationUserByEmailAddress() throws Exception {
        ApplicationUser applicationUser = DomainUtil.getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser);
        ApplicationUser userByUserName = applicationUserDao.getApplicationUserByEmailAddress(applicationUser.getEmailAddress());
        assertEquals(userByUserName.getFirstName(), applicationUser.getFirstName());
        applicationUserDao.deleteApplicationUser(applicationUser.getId());
    }

    @Test
    public void testGetApplicationUsers() throws Exception {
        ApplicationUser applicationUser1 = DomainUtil.getTestApplicationUser();
        ApplicationUser applicationUser2 = DomainUtil.getTestApplicationUser();
        applicationUserDao.addApplicationUser(applicationUser1);
        applicationUserDao.addApplicationUser(applicationUser2);
        List<ApplicationUser> applicationUsers = applicationUserDao.getApplicationUsers();
        assertEquals(applicationUsers.size(), 2);
    }

}
