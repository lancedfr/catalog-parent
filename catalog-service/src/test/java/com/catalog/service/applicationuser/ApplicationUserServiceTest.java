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

package com.catalog.service.applicationuser;

import com.catalog.repository.dao.ApplicationUserDao;
import com.catalog.repository.dao.exception.DaoException;
import com.catalog.repository.domain.ApplicationUser;
import com.catalog.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * ApplicationUserServiceTest to test Application User Services
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
    public void testAddApplicationUser() throws ServiceException, DaoException {
        applicationUserService.addApplicationUser(applicationUser);
        verify(applicationUserDao, times(1)).addApplicationUser(applicationUser);
    }

    @Test(expected = ServiceException.class)
    public void testAddApplicationUserServiceException() throws ServiceException, DaoException {
        doThrow(DaoException.class).when(applicationUserDao).addApplicationUser(applicationUser);
        applicationUserService.addApplicationUser(applicationUser);
        verify(applicationUserDao, times(1)).addApplicationUser(applicationUser);
    }

    @Test
    public void testGetApplicationUser() {
        when(applicationUserDao.getApplicationUser(anyInt())).thenReturn(applicationUser);
        ApplicationUser applicationUser1 = applicationUserService.getApplicationUser(Integer.MAX_VALUE);
        assertNotNull(applicationUser1);
    }
}
