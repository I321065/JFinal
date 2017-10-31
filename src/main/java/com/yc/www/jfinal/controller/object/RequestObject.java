package com.yc.www.jfinal.controller.object;

/**
 * Created by I321065 on 10/19/2017.
 */
public class RequestObject {

    public String publicKey;
    public String username;
    public String password;

    public String title;
    public String content;

    public long articleId;
    public String articleUserId;//article/list

    public long userId;
    public int commentOverall;
    public String commentDetail;

    public String token;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(String articleUserId) {
        this.articleUserId = articleUserId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getCommentOverall() {
        return commentOverall;
    }

    public void setCommentOverall(int commentOverall) {
        this.commentOverall = commentOverall;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
