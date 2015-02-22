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

import com.catalog.repository.domain.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addArticle(Article article) {
        Article addArticle = getArticle(article.getId());
        if (addArticle != null) {
            article.setId(addArticle.getId());
            updateArticle(article);
        } else {
            getCurrentSession().save(article);
        }
    }

    @Override
    public void deleteArticle(Article article) {
        Article deleteArticle = getArticle(article.getId());
        if (deleteArticle != null) {
            getCurrentSession().delete(deleteArticle);
        }
    }

    @Override
    public void updateArticle(Article article) {
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
        }
    }

    @Override
    public Article getArticle(Integer id) {
        return (Article) getCurrentSession().get(Article.class, id);
    }

    @Override
    public Article getArticleByName(String name) {
        return (Article) getCurrentSession().createCriteria(Article.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> getArticles() {
        return getCurrentSession().createCriteria(Article.class).list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
