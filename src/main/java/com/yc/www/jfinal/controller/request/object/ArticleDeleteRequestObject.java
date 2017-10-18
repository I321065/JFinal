package com.yc.www.jfinal.controller.request.object;

/**
 * Created by superuser on 10/17/17.
 */
public class ArticleDeleteRequestObject {

    public int articleId;

    public ArticleDeleteRequestObject(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
}
