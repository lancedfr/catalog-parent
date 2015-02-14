package com.catalog.repository.dao;

import com.catalog.repository.domain.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
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
