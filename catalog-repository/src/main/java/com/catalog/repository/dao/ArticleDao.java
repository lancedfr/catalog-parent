package com.catalog.repository.dao;

import com.catalog.repository.domain.Article;

import java.util.List;

/**
 * Created by ShaneMK on 2015/02/13.
 */
public interface ArticleDao {

    public void addArticle(Article article);

    public void deleteArticle(Article article);

    public void updateArticle(Article article);

    public Article getArticle(Integer id);

    public Article getArticleByName(String name);

    List<Article> getArticles();

}
