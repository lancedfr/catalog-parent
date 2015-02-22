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
 * ArticleDaoImplIT integration test
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
    public void testGetArticle() throws DaoException {
        //Creating a new Article
        Article testArticle = DomainUtil.getTestArticle();
        articleDao.addArticle(testArticle);

        //Testing the Get of the Article
        Article getArticle = articleDao.getArticle(testArticle.getId());

        //Assert the objects
        assertEquals(getArticle.getName(), testArticle.getName());

        articleDao.deleteArticle(testArticle.getId());
    }

    @Test
    public void testUpdateArticle() throws DaoException {
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
        articleDao.deleteArticle(testArticle.getId());
    }

    @Test
    public void testAddArticle() throws DaoException {
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

        articleDao.deleteArticle(testArticle.getId());
        articleDao.deleteArticle(testSecondArticle.getId());
    }

    @Test
    public void testDeleteArticle() throws DaoException {
        //Create Article
        Article testArticle = DomainUtil.getTestArticle();
        articleDao.addArticle(testArticle);

        //Delete Article
        Article deleteArticle = articleDao.getArticle(testArticle.getId());
        articleDao.deleteArticle(deleteArticle.getId());

        List<Article> articles = articleDao.getArticles();
        assertEquals(0, articles.size());
    }

    @Test
    public void testFindArticleByName() throws DaoException {
        //Create Article
        Article testArticle = DomainUtil.getTestArticle();
        articleDao.addArticle(testArticle);

        //Find Article
        Article findArticle = articleDao.getArticleByName(testArticle.getName());

        assertEquals(findArticle.getBarcode(), testArticle.getBarcode());
        articleDao.deleteArticle(testArticle.getId());
    }
}
