package com.yc.www.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.controller.request.object.ArticleRequestObject;
import com.yc.www.jfinal.service.article.ArticleService;
import com.yc.www.jfinal.service.article.CommentService;
import com.yc.www.jfinal.service.article.bean.Article;
import com.yc.www.jfinal.service.article.bean.ArticleVO;
import com.yc.www.jfinal.service.interceptor.UserTokenInterceptor;
import com.yc.www.jfinal.service.result.json.Result;
import com.yc.www.jfinal.service.utils.ParseRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by superuser on 9/18/17.
 */
public class ArticleController extends Controller {

    ArticleService articleService = new ArticleService();

    CommentService commentService = new CommentService();

    @ActionKey("/article/save")
    //@Before(UserTokenInterceptor.class)
    public void save() {
        //User user = getAttr("user");//get user from token
        //int userId = user.getUserId();
        int userId = 1;

        try {
            ArticleRequestObject articleRequestObject = ParseRequest.getObjectFromRequest(ArticleRequestObject.class, this);
            String articleTitle = BeanUtils.getProperty(articleRequestObject, "title");
            String articleContent = BeanUtils.getProperty(articleRequestObject, "content");

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @ActionKey("/article/update")
    @Before(UserTokenInterceptor.class)
    public void update() {

        renderJson("you can edit");
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
}
