package com.yc.www.jfinal.service.vo;

/**
 * Created by superuser on 10/31/17.
 */
public class CommentVO {
    public long articleId;
    public int commentOverall;

    public CommentVO(long articleId, int commentOverall) {
        this.articleId = articleId;
        this.commentOverall = commentOverall;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getCommentOverall() {
        return commentOverall;
    }

    public void setCommentOverall(int commentOverall) {
        this.commentOverall = commentOverall;
    }
}
