package com.catalog.service;

import com.catalog.repository.dao.ApplicationUserDao;
import com.catalog.repository.domain.ApplicationUser;
import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
