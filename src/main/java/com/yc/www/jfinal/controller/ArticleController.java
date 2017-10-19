package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import com.yc.www.jfinal.controller.object.*;
import com.yc.www.jfinal.service.article.ArticleService;
import com.yc.www.jfinal.service.article.CommentService;
import com.yc.www.jfinal.service.article.bean.Article;
import com.yc.www.jfinal.service.article.bean.ArticleVO;
import com.yc.www.jfinal.service.article.bean.Comment;
import com.yc.www.jfinal.service.interceptor.UserTokenInterceptor;
import com.yc.www.jfinal.service.result.json.Result;
import com.yc.www.jfinal.service.utils.ParseRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by superuser on 9/18/17.
 */
public class ArticleController extends Controller {

    Logger log = LogManager.getLogger(ArticleController.class);

    ArticleService articleService = new ArticleService();

    CommentService commentService = new CommentService();

    @ActionKey("/article/save")
    //@Before(UserTokenInterceptor.class)
    public void save() {
        //User user = getAttr("user");//get user from token
        //int userId = user.getUserId();
        int userId = 1;

        try {
            RequestObject articleRequestObject = ParseRequest.getObjectFromRequest(RequestObject.class, this);
            String articleTitle = articleRequestObject.getTitle();
            String articleContent = articleRequestObject.getContent();
            if(StringUtils.isBlank(articleTitle) || StringUtils.isBlank(articleContent)) {
                renderJson("title or content can not be null");
                return;
            }

            Article article = articleService.createArticle(articleTitle, articleContent, userId);
            Result result = null;
            if(article != null) {
                result = new Result(article);
            }else{
                result = new Result("something wrong happened, please contact administrator");
            }
            renderJson(result);
            return;
        } catch (IOException e) {
            log.error("catch exception", e);
        }
        renderJson(new Result("something wrong happened, please contact administrator"));
    }

    @ActionKey("/article/update")
    @Before(UserTokenInterceptor.class)
    public void update() {
        renderJson("you can edit");
    }

    @ActionKey("/article/delete")
    public void delete() {
        Result result = null;
        try {
            //ArticleDeleteRequestObject object = ParseRequest.getObjectFromRequest(ArticleDeleteRequestObject.class, this);
            RequestObject object = ParseRequest.getObjectFromRequest(RequestObject.class, this);
            long articleId = object.getArticleId();
            boolean isSucceed = articleService.deleteArticleById(articleId);
            if(isSucceed) {
                result = new Result(new ArticleDeleteRequestObject(articleId));
            }else {
                result = new Result("delete the article failed");
            }
        } catch (IOException e) {
            log.error("catch exception", e);
            result = new Result("delete the article failed");
        }
        renderJson(result);

    }

    @ActionKey("/article/list")
    //@Before(UserTokenInterceptor.class)
    public void list() {
        List<ArticleVO> articleVOs = null;
        String articleUserId = getPara("articleUserId");
        if(!StringUtils.isBlank(articleUserId)) {
            articleVOs = articleService.listAllArticles(Integer.parseInt(articleUserId));
        }else {
            articleVOs = articleService.listAllArticles();
        }
        renderJson(new Result(articleVOs));
    }

    @ActionKey("/article/comment")
    //@Before(UserTokenInterceptor.class)
    public void comment() {
        /*int articleId = 1;
        int userId = 1;
        String commentDetail = "";
        int commentOverall = 1;*/

        try {
            RequestObject object = ParseRequest.getObjectFromRequest(RequestObject.class, this);
            Comment comment = new Comment();
            comment.setArticleId(object.getArticleId());
            comment.setCommentUserId(object.getUserId());
            comment.setCommentDetail(object.getCommentDetail());
            comment.setCommentOverall(object.getCommentOverall());
            Comment saveBean = commentService.save(comment);
        } catch (IOException e) {
            log.error("catch exceptio");
        }
        renderJson("jfljaldfjjsdfjalsjdfl");
    }

}
