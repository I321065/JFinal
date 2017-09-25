package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.yc.www.jfinal.service.article.ArticleService;
import com.yc.www.jfinal.service.article.CommentService;
import com.yc.www.jfinal.service.article.bean.Article;
import com.yc.www.jfinal.service.interceptor.UserTokenInterceptor;
import com.yc.www.jfinal.service.result.json.Result;
import com.yc.www.jfinal.service.user.bean.User;

import java.util.List;

/**
 * Created by superuser on 9/18/17.
 */
public class ArticleController extends Controller {

    ArticleService articleService = new ArticleService();

    CommentService commentService = new CommentService();

    @ActionKey("/article/save")
    @Before(UserTokenInterceptor.class)
    public void save() {
        User user = getAttr("user");//get user from token
        int userId = user.getUserId();

        String articleTitle = getPara();
        String articleContent = getPara();

        Article article = articleService.createArticle(articleTitle, articleContent, userId);
        Result result = null;
        if(article != null) {
            result = new Result(article);
        }else{
            result = new Result("something wrong happened, please contact administrator");
        }
        renderJson(result);
    }

    @ActionKey("/article/update")
    @Before(UserTokenInterceptor.class)
    public void update() {

        renderJson("you can edit");
    }

    @ActionKey("/article/list")
    @Before(UserTokenInterceptor.class)
    public void list() {
        String articleUserId = getPara();
        List<Record> articles = articleService.listAllArticles();
        renderJson(new Result(articles));
    }
}
