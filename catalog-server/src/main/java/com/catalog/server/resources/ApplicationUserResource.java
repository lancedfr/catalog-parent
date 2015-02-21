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
import com.catalog.service.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lance on 13/02/2015.
 */
@Controller
public class ApplicationUserResource {

    private ApplicationUserService applicationUserService;

    @ResponseBody
    @RequestMapping(value = "/rest/applicationuser", method = RequestMethod.POST)
    public ApplicationUser addApplicationUser(@RequestBody ApplicationUser applicationUser) {
        try {
            applicationUserService.addApplicationUser(applicationUser);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return applicationUser;
    }

    @ResponseBody
    @RequestMapping(value = "/rest/applicationuser/{id}", method = RequestMethod.GET)
    public ApplicationUser getApplicationUser(@PathVariable("id") Integer id) {
        try {
            return applicationUserService.getApplicationUser(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setApplicationUserService(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }
}
