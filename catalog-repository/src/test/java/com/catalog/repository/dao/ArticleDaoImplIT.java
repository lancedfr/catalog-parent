package com.catalog.repository.dao;

import com.catalog.repository.domain.Article;
import com.catalog.repository.spring.config.DefaultDaoTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by ShaneMK on 2015/02/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DefaultDaoTestConfig.class})
public class ArticleDaoImplIT {
    @Autowired
    private ApplicationContext applicationContext;
    private ArticleDao articleDao;

    @Before
    public void before()
    {
        articleDao = applicationContext.getBean(ArticleDao.class);
    }

    public Article getTestArticle(){
        Article article = new Article();
        article.setName("Product");
        article.setShortDescription("ProductDescription");
        article.setLongDescription("ProductDescriptionForArticle");
        article.setCreatedDate(Date.valueOf("2015-05-05"));
        article.setPhasedOutDate(Date.valueOf("2015-05-05"));
        article.setDeletedDate(Date.valueOf("2015-05-06"));
        article.setPrice(BigDecimal.ONE);
        article.setBarcode("BarCode");
        return article;
    }

    @Test
    public void testGetArticle()throws Exception
    {
        //Creating a new Article
        Article testArticle = getTestArticle();
        articleDao.addArticle(testArticle);

        //Testing the Get of the Article
        Article getArticle = articleDao.findArticle(testArticle.getId());

        //Assert the objects
        assertEquals(getArticle.getName(), testArticle.getName());

        articleDao.deleteArticle(testArticle);
    }

    @Test
    public void testUpdateArticle() throws Exception
    {
        //Create Article
        Article testArticle = getTestArticle();
        articleDao.addArticle(testArticle);

        //Update Article
        Article updateArticle = articleDao.findArticle(testArticle.getId());
        updateArticle.setName("New Product");
        articleDao.amendArticle(updateArticle);

        //Get Article
        Article updatedArticle = articleDao.findArticle(updateArticle.getId());

        assertEquals("New Product", updatedArticle.getName());
        articleDao.deleteArticle(testArticle);
    }

    @Test
    public void testAddArticle() throws  Exception
    {
        //Create Article
        Article testArticle = getTestArticle();

        //Add Article
        articleDao.addArticle(testArticle);

        //Add New Article
        Article testSecondArticle = getTestArticle();
        testSecondArticle.setName("New Product");
        testSecondArticle.setBarcode("New Barcode");

        articleDao.addArticle(testSecondArticle);

        //Check Articles
        List<Article> articleList = articleDao.getArticleList();

        assertEquals(2,articleList.size());

        articleDao.deleteArticle(testArticle);
    }

    @Test
    public void testDeleteArticle() throws Exception
    {
        //Create Article
        Article testArticle = getTestArticle();
        articleDao.addArticle(testArticle);

        //Delete Article
        Article deleteArticle = articleDao.findArticle(testArticle.getId());
        articleDao.deleteArticle(deleteArticle);

        List<Article> articles = articleDao.getArticleList();
        assertEquals(0, articles.size());
        articleDao.deleteArticle(testArticle);
    }

    @Test
    public void testFindArticleByName() throws Exception
    {
        //Create Article
        Article testArticle = getTestArticle();
        articleDao.addArticle(testArticle);

        //Find Article
        Article findArticle = articleDao.findArticleByName("Product");

        assertEquals(findArticle.getBarcode(), testArticle.getBarcode());
        articleDao.deleteArticle(testArticle);
    }
}
