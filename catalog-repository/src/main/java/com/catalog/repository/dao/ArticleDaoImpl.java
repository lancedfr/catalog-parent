package com.catalog.repository.dao;

import com.catalog.repository.domain.Article;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ShaneV on 2015/02/13.
 */
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addArticle(Article article) {

    }

    @Override
    public void deleteArticle(Article article) {

    }

    @Override
    public void amendArticle(Article article) {

    }

    @Override
    public Article findArticle(Integer id) {
        return null;
    }

    @Override
    public Article findArticleByName(String name) {
        return null;
    }

    @Override
    public List<Article> getArticleList() {
        return null;
    }
}
