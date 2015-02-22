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
import com.catalog.repository.domain.Article;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * ArticleDaoImpl
 * Created by ShaneMK on 2015/02/13.
 */

@Repository
@Transactional
public class ArticleDaoImpl implements ArticleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleDaoImpl.class);

    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addArticle(Article article) throws DaoException {
        LOGGER.debug("Adding article");
        Article addArticle = getArticle(article.getId());
        if (addArticle != null) {
            article.setId(addArticle.getId());
            updateArticle(article);
        } else {
            LOGGER.warn("Adding id:{} found, performing update", article.getId());
            getCurrentSession().save(article);
        }
    }

    @Override
    public void deleteArticle(Integer id) {
        LOGGER.debug("Deleting application user");
        Article article = new Article();
        article.setId(id);
        getCurrentSession().delete(article);
    }

    @Override
    public void updateArticle(Article article) throws DaoException {
        LOGGER.debug("Updating article");
        Article updateArticle = getArticle(article.getId());
        if (updateArticle != null) {
            updateArticle.setName(article.getName());
            updateArticle.setBarcode(article.getBarcode());
            updateArticle.setCreatedDate(article.getCreatedDate());
            updateArticle.setDeletedDate(article.getDeletedDate());
            updateArticle.setPhasedOutDate(article.getPhasedOutDate());
            updateArticle.setLongDescription(article.getLongDescription());
            updateArticle.setShortDescription(article.getShortDescription());
            updateArticle.setPrice(article.getPrice());
            getCurrentSession().update(updateArticle);
        } else {
            String articleString = ReflectionToStringBuilder.toString(article, ToStringStyle.DEFAULT_STYLE);
            LOGGER.error("Article to update not found: {}", articleString);
            throw new DaoException("Article to update not found: " + articleString);
        }
    }

    @Override
    public Article getArticle(Integer id) {
        LOGGER.debug("Get article user id={}", id);
        return (Article) getCurrentSession().get(Article.class, id);
    }

    @Override
    public Article getArticleByName(String name) {
        LOGGER.debug("Get article user name={}", name);
        return (Article) getCurrentSession().createCriteria(Article.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> getArticles() {
        LOGGER.debug("Get articles");
        return getCurrentSession().createCriteria(Article.class).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
