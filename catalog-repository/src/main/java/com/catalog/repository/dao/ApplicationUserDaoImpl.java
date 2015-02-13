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

import com.catalog.repository.domain.ApplicationUser;
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
    public void addApplicationUser(ApplicationUser user) {
        ApplicationUser userByName = getApplicationUserByUserName(user.getFirstName());
        if (userByName == null) {
            getCurrentSession().save(user);
        } else {
            user.setId(userByName.getId());
            updateApplicationUser(user);
        }
    }

    @Override
    public void deleteApplicationUser(Integer id) {
        ApplicationUser user = getApplicationUser(id);
        if (user != null) {
            getCurrentSession().delete(user);
        }

    }

    @Override
    public void updateApplicationUser(ApplicationUser user) {
        ApplicationUser userToUpdate = getApplicationUser(user.getId());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setUserName(user.getUserName());
        userToUpdate.setAge(user.getAge());
        getCurrentSession().update(userToUpdate);
    }

    @Override
    public ApplicationUser getApplicationUser(Integer id) {
        return (ApplicationUser) getCurrentSession().get(ApplicationUser.class, id);
    }

    @Override
    public ApplicationUser getApplicationUserByUserName(String userName) {
        return (ApplicationUser) getCurrentSession().createCriteria(ApplicationUser.class)
                .add(Restrictions.eq("userName", userName)).uniqueResult();
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
