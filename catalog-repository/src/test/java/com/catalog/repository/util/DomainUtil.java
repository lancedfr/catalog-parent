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

package com.catalog.repository.util;

import com.catalog.repository.domain.ApplicationUser;
import com.catalog.repository.domain.Article;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Lance on 14/02/2015.
 */
public class DomainUtil {

    public static final int RANDOM_LENGTH = 8;

    private DomainUtil() {
        //private constructor to hide the implicit public one
    }

    public static ApplicationUser getTestApplicationUser() {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setEmailAddress(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setFirstName(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setGender(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setAge(Integer.valueOf(RandomStringUtils.randomNumeric(RANDOM_LENGTH)));
        applicationUser.setLastName(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        applicationUser.setPassword(RandomStringUtils.randomAlphanumeric(RANDOM_LENGTH));
        return applicationUser;
    }

    public static Article getTestArticle() {
        Article article = new Article();
        article.setName(RandomStringUtils.random(RANDOM_LENGTH));
        article.setShortDescription(RandomStringUtils.random(RANDOM_LENGTH));
        article.setLongDescription(RandomStringUtils.random(RANDOM_LENGTH));
        article.setCreatedDate(Calendar.getInstance().getTime());
        article.setPhasedOutDate(Calendar.getInstance().getTime());
        article.setDeletedDate(Calendar.getInstance().getTime());
        article.setPrice(BigDecimal.ONE);
        article.setBarcode(RandomStringUtils.randomNumeric(RANDOM_LENGTH));
        return article;
    }
}
