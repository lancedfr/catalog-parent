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

package com.catalog.webapp.resources;

import com.catalog.repository.domain.ApplicationUser;
import com.catalog.service.applicationuser.ApplicationUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ApplicationUserResourcesTest to test Application user resource
 * Created by Lance on 14/02/2015.
 */
public class ApplicationUserResourcesTest {

    private static final int RANDOM_LENGTH = 8;
    private static final int APPLICATION_USER_ID = 1;

    @Mock
    private ApplicationUserService applicationUserService;
    @InjectMocks
    private ApplicationUserResource applicationUserResource;
    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        applicationUserResource.setApplicationUserService(applicationUserService);
        mockMvc = MockMvcBuilders.standaloneSetup(applicationUserResource).build();
    }

    @Test
    public void testGetApplicationUser() throws Exception {
        when(applicationUserService.getApplicationUser(APPLICATION_USER_ID)).thenReturn(getTestApplicationUser());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/applicationuser/{id}".replace("{id}", "1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(APPLICATION_USER_ID)));

        verify(applicationUserService, times(APPLICATION_USER_ID)).getApplicationUser(APPLICATION_USER_ID);
    }

    @Test
    public void testAddApplicationUser() throws Exception {
        String applicationUserJsonTest = new ObjectMapper().writeValueAsString(getTestApplicationUser());

        mockMvc.perform(post("/rest/applicationuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(applicationUserJsonTest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(APPLICATION_USER_ID)));

        verify(applicationUserService, times(APPLICATION_USER_ID)).addApplicationUser(any(ApplicationUser.class));
        verifyNoMoreInteractions(applicationUserService);
    }

    private ApplicationUser getTestApplicationUser() {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(APPLICATION_USER_ID);
        applicationUser.setEmailAddress(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setFirstName(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setGender(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setAge(Integer.valueOf(RandomStringUtils.randomNumeric(RANDOM_LENGTH)));
        applicationUser.setLastName(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setPassword(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        return applicationUser;
    }
}
