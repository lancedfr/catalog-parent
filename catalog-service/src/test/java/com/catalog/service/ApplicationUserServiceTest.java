package com.catalog.service;

import com.catalog.repository.dao.ApplicationUserDao;
import com.catalog.repository.domain.ApplicationUser;
import com.catalog.service.applicationuser.ApplicationUserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by Lance on 12/02/2015.
 */
public class ApplicationUserServiceTest {

    @Mock
    private ApplicationUser applicationUser;
    @Mock
    private ApplicationUserDao applicationUserDao;
    @InjectMocks
    private ApplicationUserServiceImpl applicationUserService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        applicationUserService = new ApplicationUserServiceImpl();
        applicationUserService.setApplicationUserDao(applicationUserDao);
    }

    @Test
    public void testGetApplicationUser() {
        when(applicationUserDao.getApplicationUser(anyInt())).thenReturn(applicationUser);
        ApplicationUser applicationUser1 = applicationUserService.getApplicationUser(Integer.MAX_VALUE);
        assertNotNull(applicationUser1);
    }
}
