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
import com.catalog.service.exception.ServiceException;
import com.catalog.webapp.exception.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * ApplicationUserResource to expose services for ApplicationUser externally
 * Created by Lance on 13/02/2015.
 */
@RestController
public class ApplicationUserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUserResource.class);

    private ApplicationUserService applicationUserService;

    /**
     * Gets a dummy ApplicationUser for testing
     *
     * @return the ApplicationUser product
     */
    @ResponseBody
    @RequestMapping(value = "/rest/applicationuser/dummy", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApplicationUser getDummyApplicationUser() {
        LOGGER.debug("Getting dummy user");
        return new ApplicationUser();
    }

    @ResponseBody
    @RequestMapping(value = "/rest/applicationuser", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApplicationUser addApplicationUser(@RequestBody ApplicationUser applicationUser) {
        try {
            LOGGER.debug("Adding application user {}", applicationUser);
            applicationUserService.addApplicationUser(applicationUser);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ResourceException(e);
        }
        return applicationUser;
    }

    /**
     * Gets an ApplicationUser
     *
     * @param applicationUserId, ApplicationUser ID to fetch
     * @return ApplicationUser
     */
    @ResponseBody
    @RequestMapping(value = "/rest/applicationuser/{id}", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApplicationUser getApplicationUser(@PathVariable("id") Integer applicationUserId) {
        LOGGER.debug("Getting application user id={}", applicationUserId);
        return applicationUserService.getApplicationUser(applicationUserId);
    }

    public void setApplicationUserService(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }
}
