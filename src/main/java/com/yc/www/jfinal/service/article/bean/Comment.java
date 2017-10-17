package com.yc.www.jfinal.service.article.bean;

import com.yc.www.jfinal.service.common.Entity;

import java.util.Date;

/**
 * Created by I321065 on 9/25/2017.
 */
public class Comment extends Entity<Comment> {

    public static final Comment dao = new Comment().dao();

    private long commentId;

    private int articleId;

    private int commentUserId;

    private String commentDetail;

    private int commentOverall;//[-1, 0, 1]

    private Date createDate;

    private Date updateDate;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public int getCommentOverall() {
        return commentOverall;
    }

    public void setCommentOverall(int commentOverall) {
        this.commentOverall = commentOverall;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
