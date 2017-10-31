package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

import com.yc.www.jfinal.controller.object.*;
import com.yc.www.jfinal.service.annotaion.PermissionCheck;
import com.yc.www.jfinal.service.ArticleService;
import com.yc.www.jfinal.service.CommentService;
import com.yc.www.jfinal.service.common.Role;
import com.yc.www.jfinal.service.entity.Article;
import com.yc.www.jfinal.service.vo.ArticleVO;
import com.yc.www.jfinal.service.entity.Comment;
import com.yc.www.jfinal.service.common.Constants;
import com.yc.www.jfinal.service.interceptor.UserTokenInterceptor;
import com.yc.www.jfinal.service.result.ResponseError;
import com.yc.www.jfinal.service.result.ResponseResult;
import com.yc.www.jfinal.service.utils.ParseRequest;
import com.yc.www.jfinal.service.vo.CommentVO;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static com.yc.www.jfinal.service.common.Constants.CONTACT_ADMINISTRATOR;
import static com.yc.www.jfinal.service.common.Constants.USER_ID;

/**
 * Created by superuser on 9/18/17.
 */
public class ArticleController extends Controller {

    private static final Logger log = LogManager.getLogger(ArticleController.class);

    ArticleService articleService = new ArticleService();

    CommentService commentService = new CommentService();

    @ActionKey("/article/save")
    @Before(UserTokenInterceptor.class)
    @PermissionCheck(Role.User)
    public void save() {
        //User user = getAttr("user");//get user from token
        //int userId = user.getUserId();
        RequestObject articleRequestObject = ParseRequest.getObjectFromRequest(RequestObject.class, this);
        if(isPostRequest(articleRequestObject)) {
            long userId = getAttr(USER_ID);
            String articleTitle = articleRequestObject.getTitle();
            String articleContent = articleRequestObject.getContent();
            if(StringUtils.isBlank(articleTitle) || StringUtils.isBlank(articleContent)) {
                renderJson("title or content can not be null");
                return;
            }

            Article article = articleService.createArticle(articleTitle, articleContent, userId);
            ResponseResult result = null;
            if(article != null) {
                result = new ResponseResult(article);
            }
            renderJson(result);
            return;
        }
        log.error("this request must be post request");
        renderJson(new ResponseResult(new ResponseError("something wrong happened, please contact administrator")));
    }

    @ActionKey("/article/update")
    @Before(UserTokenInterceptor.class)
    @PermissionCheck(Role.User)
    public void update() {
        renderJson("you can edit");
    }

    @ActionKey("/article/delete")
    public void delete() {
        ResponseResult result = null;
        RequestObject object = ParseRequest.getObjectFromRequest(RequestObject.class, this);
        if(!isPostRequest(object)) {
            long articleId = object.getArticleId();
            boolean isSucceed = articleService.deleteArticleById(articleId);
            if(isSucceed) {
                result = new ResponseResult(articleId);
            }
            renderJson(result);
            return;
        }
        log.error("this request must be post request");
        renderJson(new ResponseResult(new ResponseError("something wrong happened, please contact administrator")));
    }

    @ActionKey("/article/all")
    public void all() {
        List<ArticleVO> articleVOs = articleService.listAllArticles();
        renderJson(new ResponseResult(articleVOs));
    }

    @ActionKey("/article/userId")
    @Before(UserTokenInterceptor.class)
    public void list() {
        List<ArticleVO> articleVOs = null;
        RequestObject object = ParseRequest.getObjectFromRequest(RequestObject.class, this);
        if(isPostRequest(object)) {
            String articleUserId = object.getArticleUserId();
            if(!StringUtils.isBlank(articleUserId)) {
                articleVOs = articleService.listAllArticles(Long.parseLong(articleUserId));
                renderJson(new ResponseResult(articleVOs));
                return;
            }else {
                log.error("the request is post, but articleId is null");
            }
        }else {
            log.error("this is not post request");
        }
        renderJson(new ResponseResult(new ResponseError(CONTACT_ADMINISTRATOR)));
    }

    @ActionKey("/article/comment")
    @Before(UserTokenInterceptor.class)
    @PermissionCheck(Role.User)
    public void comment() {
        /*int articleId = 1;
        int userId = 1;
        String commentDetail = "";
        int commentOverall = 1;*/
        Long userId = (Long)getAttr(USER_ID);
        if(userId == null) {
            renderJson(new ResponseResult(new Error("userId is not right, userId=" + userId)));
            return;
        }
        RequestObject object =ParseRequest.getObjectFromRequest(RequestObject.class, this);
        long articleId = -1;
        int commentOverall = -1;
        if(isPostRequest(object)) {
            articleId = object.getArticleId();
            commentOverall = object.getCommentOverall();
            if(articleId > 0 && commentOverall > 0) {
                Comment comment = new Comment();
                comment.setArticleId(articleId);
                comment.setCommentUserId(userId);
                //comment.setCommentDetail(object.getCommentDetail());
                comment.setCommentOverall(object.getCommentOverall());
                Comment saveBean = commentService.save(comment);
                renderJson(new ResponseResult(new CommentVO(articleId, commentOverall)));
                return;
            }
            log.error("can not parse articleId or commentOverall, articleId=" + articleId + ", commentOverall=" + commentOverall);
        }else {
            log.error("this request must be post request");
        }
        renderJson(new ResponseResult(new ResponseError("something wrong happened, please contact administrator")));
        return;
    }

    private boolean isPostRequest(RequestObject object) {
        if(object == null) {
            return false;
        }
        return true;
    }

}
