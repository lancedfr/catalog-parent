package com.catalog.repository.dao;

import com.catalog.repository.domain.Article;
import com.catalog.repository.spring.config.DefaultDaoTestConfig;
import com.catalog.repository.util.DomainUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void before() {
        articleDao = applicationContext.getBean(ArticleDao.class);
    }

    @Test
    public void testGetArticle() throws Exception {
        //Creating a new Article
        Article testArticle = DomainUtil.getTestArticle();
        articleDao.addArticle(testArticle);

        //Testing the Get of the Article
        Article getArticle = articleDao.getArticle(testArticle.getId());

        //Assert the objects
        assertEquals(getArticle.getName(), testArticle.getName());

        articleDao.deleteArticle(testArticle);
    }

    @Test
    public void testUpdateArticle() throws Exception {
        //Create Article
        Article testArticle = DomainUtil.getTestArticle();
        articleDao.addArticle(testArticle);

        //Update Article
        Article updateArticle = articleDao.getArticle(testArticle.getId());
        updateArticle.setName("New Product");
        articleDao.updateArticle(updateArticle);

        //Get Article
        Article updatedArticle = articleDao.getArticle(updateArticle.getId());

        assertEquals("New Product", updatedArticle.getName());
        articleDao.deleteArticle(testArticle);
    }

    @Test
    public void testAddArticle() throws Exception {
        //Create Article
        Article testArticle = DomainUtil.getTestArticle();

        //Add Article
        articleDao.addArticle(testArticle);

        //Add New Article
        Article testSecondArticle = DomainUtil.getTestArticle();
        testSecondArticle.setName("New Product");
        testSecondArticle.setBarcode("New Barcode");

        articleDao.addArticle(testSecondArticle);

        //Check Articles
        List<Article> articleList = articleDao.getArticles();

        assertEquals(2, articleList.size());

        articleDao.deleteArticle(testArticle);
        articleDao.deleteArticle(testSecondArticle);
    }

    @Test
    public void testDeleteArticle() throws Exception {
        //Create Article
        Article testArticle = DomainUtil.getTestArticle();
        articleDao.addArticle(testArticle);

        //Delete Article
        Article deleteArticle = articleDao.getArticle(testArticle.getId());
        articleDao.deleteArticle(deleteArticle);

        List<Article> articles = articleDao.getArticles();
        assertEquals(0, articles.size());
        articleDao.deleteArticle(testArticle);
    }

    @Test
    public void testFindArticleByName() throws Exception {
        //Create Article
        Article testArticle = DomainUtil.getTestArticle();
        articleDao.addArticle(testArticle);

        //Find Article
        Article findArticle = articleDao.getArticleByName(testArticle.getName());

        assertEquals(findArticle.getBarcode(), testArticle.getBarcode());
        articleDao.deleteArticle(testArticle);
    }
}
