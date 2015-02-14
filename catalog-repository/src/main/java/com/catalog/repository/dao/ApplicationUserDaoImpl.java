/**
 * EFD (Economic Freedom Developers) 
 * (Lance Reid, Nash Naidoo, Flippie van Schalkwyk, Tohhier Allie)
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.catalog.repository.dao;

import com.catalog.repository.dao.exception.DaoException;
import com.catalog.repository.domain.ApplicationUser;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ApplicationUserDaoImpl implements ApplicationUserDao {

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
        ApplicationUser applicationUserByEmailAddress = getApplicationUserByEmailAddress(user.getEmailAddress());
        if (applicationUserByEmailAddress == null) {
            getCurrentSession().save(user);
        } else {
            user.setId(applicationUserByEmailAddress.getId());
            updateApplicationUser(user);
        }
    }

    @Override
    public void deleteApplicationUser(Integer id) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(id);
        getCurrentSession().delete(applicationUser);
    }

    @Override
    public void updateApplicationUser(ApplicationUser user) throws DaoException {
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
            ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE);
            throw new DaoException("User to update not found: " + ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE));
        }
    }

    @Override
    public ApplicationUser getApplicationUser(Integer id) {
        return (ApplicationUser) getCurrentSession().get(ApplicationUser.class, id);
    }

    @Override
    public ApplicationUser getApplicationUserByEmailAddress(String emailAddress) {
        return (ApplicationUser) getCurrentSession().createCriteria(ApplicationUser.class)
                .add(Restrictions.eq("emailAddress", emailAddress)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ApplicationUser> getApplicationUsers() {
        return getCurrentSession().createCriteria(ApplicationUser.class).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
