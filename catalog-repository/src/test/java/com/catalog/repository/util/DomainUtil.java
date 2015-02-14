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

    public static ApplicationUser getTestApplicationUser() {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setEmailAddress(RandomStringUtils.randomAlphanumeric(10));
        applicationUser.setFirstName(RandomStringUtils.randomAlphanumeric(10));
        applicationUser.setGender(RandomStringUtils.randomAlphanumeric(10));
        applicationUser.setAge(Integer.valueOf(RandomStringUtils.randomNumeric(2)));
        applicationUser.setLastName(RandomStringUtils.randomAlphanumeric(10));
        applicationUser.setPassword(RandomStringUtils.randomAlphanumeric(10));
        return applicationUser;
    }

    public static Article getTestArticle() {
        Article article = new Article();
        article.setName(RandomStringUtils.random(10));
        article.setShortDescription(RandomStringUtils.random(10));
        article.setLongDescription(RandomStringUtils.random(10));
        article.setCreatedDate(Calendar.getInstance().getTime());
        article.setPhasedOutDate(Calendar.getInstance().getTime());
        article.setDeletedDate(Calendar.getInstance().getTime());
        article.setPrice(BigDecimal.ONE);
        article.setBarcode(RandomStringUtils.randomNumeric(16));
        return article;
    }
}
