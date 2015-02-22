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
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUserDaoImpl.class);

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

    /**
     * Gets the current session.
     *
     * @return the current session
     */
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addApplicationUser(ApplicationUser user) throws DaoException {
        LOGGER.debug("Adding application user");
        ApplicationUser applicationUserByEmailAddress = getApplicationUserByEmailAddress(user.getEmailAddress());
        if (applicationUserByEmailAddress == null) {
            getCurrentSession().save(user);
        } else {
            LOGGER.warn("Application user id:{} found, performing update", user.getId());
            user.setId(applicationUserByEmailAddress.getId());
            updateApplicationUser(user);
        }
    }

    @Override
    public void deleteApplicationUser(Integer id) {
        LOGGER.debug("Deleting application user");
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(id);
        getCurrentSession().delete(applicationUser);
    }

    @Override
    public void updateApplicationUser(ApplicationUser user) throws DaoException {
        LOGGER.debug("Updating application user");
        ApplicationUser userToUpdate = getApplicationUser(user.getId());
        if (userToUpdate != null) {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setEmailAddress(user.getEmailAddress());
            userToUpdate.setAge(user.getAge());
            getCurrentSession().update(userToUpdate);
        } else {
            String userString = ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE);
            LOGGER.error("User to update not found: {}", userString);
            throw new DaoException("User to update not found: " + userString);
        }
    }

    @Override
    public ApplicationUser getApplicationUser(Integer id) {
        LOGGER.debug("Get application user id={}", id);
        return (ApplicationUser) getCurrentSession().get(ApplicationUser.class, id);
    }

    @Override
    public ApplicationUser getApplicationUserByEmailAddress(String emailAddress) {
        LOGGER.debug("Get application email={}", emailAddress);
        return (ApplicationUser) getCurrentSession().createCriteria(ApplicationUser.class)
                .add(Restrictions.eq("emailAddress", emailAddress)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ApplicationUser> getApplicationUsers() {
        LOGGER.debug("Get application users");
        return getCurrentSession().createCriteria(ApplicationUser.class).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
