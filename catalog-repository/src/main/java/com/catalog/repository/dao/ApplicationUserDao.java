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

package com.catalog.repository.dao;

import com.catalog.repository.dao.exception.DaoException;
import com.catalog.repository.domain.ApplicationUser;

import java.util.List;

public interface ApplicationUserDao {

    void addApplicationUser(ApplicationUser user) throws DaoException;

    void deleteApplicationUser(Integer id);

    void updateApplicationUser(ApplicationUser user) throws DaoException;

    ApplicationUser getApplicationUser(Integer id);

    ApplicationUser getApplicationUserByEmailAddress(String userName);

    List<ApplicationUser> getApplicationUsers();
}
