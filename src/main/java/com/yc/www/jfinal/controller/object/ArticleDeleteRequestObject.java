package com.yc.www.jfinal.controller.object;

/**
 * Created by superuser on 10/17/17.
 */
public class ArticleDeleteRequestObject {

    public long articleId;

    public ArticleDeleteRequestObject(long articleId) {
        this.articleId = articleId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
