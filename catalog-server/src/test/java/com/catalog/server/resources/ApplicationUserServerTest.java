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

package com.catalog.server.resources;

import com.catalog.repository.domain.ApplicationUser;
import com.catalog.service.applicationuser.ApplicationUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Lance on 14/02/2015.
 */
public class ApplicationUserServerTest {

    public static final int RANDOM_LENGTH = 8;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Mock
    private ApplicationUserService applicationUserService;
    @Mock
    private ApplicationUserResource applicationUserResource;
    private MockMvc mockMvc;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(applicationUserResource).build();
    }

    @Test
    public void testAddApplicationUser() throws Exception {
        ApplicationUser testApplicationUser = getTestApplicationUser();
        ObjectMapper mapper = new ObjectMapper();
        String applicationUserJson = mapper.writeValueAsString(testApplicationUser);
        mockMvc.perform(post("/rest/applicationuser")//
                .contentType(APPLICATION_JSON_UTF8)//
                .content(applicationUserJson))//
                .andExpect(status().isOk())//
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
                .andExpect(jsonPath("$.id", is(1)));//
        verify(applicationUserService, times(1)).addApplicationUser(any(ApplicationUser.class));
    }

    @Test
    public void testGetProduct() throws Exception {
        ApplicationUser testApplicationUser = getTestApplicationUser();
        when(applicationUserService.getApplicationUser(1)).thenReturn(testApplicationUser);

        mockMvc.perform(get("/rest/applicationuser/{id}".replace("{id}", "1")))//
                .andExpect(status().isOk())//
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))//
                .andExpect(jsonPath("$.id", is(1)));//

        verify(applicationUserService, times(1)).getApplicationUser(1);
    }

    private ApplicationUser getTestApplicationUser() {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setEmailAddress(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setFirstName(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setGender(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setAge(Integer.valueOf(RandomStringUtils.randomNumeric(RANDOM_LENGTH)));
        applicationUser.setLastName(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setPassword(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        return applicationUser;
    }
}
