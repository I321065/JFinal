package com.yc.www.jfinal.service.article;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.yc.www.jfinal.service.article.bean.Article;
import com.yc.www.jfinal.service.utils.SaveToDBUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by I321065 on 9/25/2017.
 */
public class ArticleService {

    private static String rootPath = "/apps/articles";

    public static final Article dao = new Article().dao();

    public Article createArticle(String title, String content, int userId) {
        String filePath = saveArticleToDisk(title, content, userId);
        Article article = new Article();
        article.setArticleTitle(title);
        article.setArticleLocation(filePath);
        article.setArticleUserId(userId);
        article.setCreateDate(new Date());
        article.setUpdateDate(new Date());
        return SaveToDBUtil.saveModel(article);
    }

    private String saveArticleToDisk(String title, String content, int userId) {
        File directory = new File(rootPath + userId);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = title;
        String filePath = rootPath + userId + fileName;
        File article = new File(filePath);
        if(!article.exists()) {
            try {
                article.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //save to diskspace
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(article);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }

    public List<Record> listAllArticles() {
        return listAllArticles(-1);
    }

    public List<Record> listAllArticles(int articleUserId) {
        StringBuilder sql = new StringBuilder("select * from article");
        if(articleUserId > 0) {
            sql.append(" where articleUserId = " + articleUserId);
        }
        List<Record> articles = Db.find(sql.toString());
        return articles;
    }
}
