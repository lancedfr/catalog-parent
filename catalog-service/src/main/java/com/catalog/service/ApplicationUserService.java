package com.catalog.service;

import com.catalog.repository.domain.ApplicationUser;

/**
 * Created by Lance on 12/02/2015.
 */
public interface ApplicationUserService {
    ApplicationUser getApplicationUser(Integer id);
}
