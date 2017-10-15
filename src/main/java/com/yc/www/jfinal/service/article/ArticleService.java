package com.yc.www.jfinal.service.article;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.yc.www.jfinal.service.article.bean.Article;
import com.yc.www.jfinal.service.article.bean.ArticleVO;
import com.yc.www.jfinal.service.user.UserService;
import com.yc.www.jfinal.service.utils.SaveToDBUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by I321065 on 9/25/2017.
 */
public class ArticleService {

    private static final Logger log = LogManager.getLogger(ArticleService.class);

    private UserService userService = new UserService();

    public static final Article dao = new Article().dao();

    private static String rootPath = "/home/superuser/workspace/temp/ironman/articles"; //local
    //private static String rootPath = "/apps/ironman/articles"; //production

    public Article createArticle(String title, String content, int userId) {
        if(StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
            return null;
        }
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
        String dirPath = rootPath + File.separator + userId + File.separator;
        File dir = new File(dirPath);
        if(!dir.exists()) {
            if(!dir.mkdir()) {
                log.error("failed to create directory, path=" + dir.getAbsolutePath());
                return null;
            }

        }
        String filePath = dirPath + title + "_" + new Date().getTime();
        File article = new File(filePath);
        if(!article.exists()) {
            try {
                if(!article.createNewFile()) {
                    log.error("failed to create article file, path=" + article.getAbsolutePath());
                }
            } catch (IOException e) {
                log.error("catch exception", e);
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
            log.error("catch some exception", e);
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    log.error("catch exception", e);
                }
            }
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    log.error("catch exception", e);
                }
            }
        }
        return filePath;
    }

    public List<ArticleVO> listAllArticles() {
        return listAllArticles(-1);
    }

    public List<ArticleVO> listAllArticles(int articleUserId) {
        StringBuilder sql = new StringBuilder("select * from article");
        if(articleUserId > 0) {
            sql.append(" where articleUserId = " + articleUserId);
        }
        List<Record> articles = Db.find(sql.toString());
        return convertToArticleVO(articles);
    }

    private List<ArticleVO> convertToArticleVO(List<Record> recoreds) {
        if(recoreds == null || recoreds.isEmpty()) {
            return null;
        }

        List<ArticleVO> articleVOs = new ArrayList<ArticleVO>(recoreds.size());

        for(Record re : recoreds) {
            ArticleVO vo = new ArticleVO();
            vo.setTitle(re.getStr("articleTitle"));
            vo.setContent(getArticleContent(re.getStr("articleLocation")));
            long articleUserId = re.getLong("articleUserId");
            vo.setUserName(userService.getUserNameById((int)articleUserId));
            articleVOs.add(vo);
        }
        return articleVOs;
    }

    private String getArticleContent(String filePath) {
        if(StringUtils.isBlank(filePath)) {
            log.error("file path is null");
            return null;
        }
        File file = new File(filePath);
        if(!file.exists()) {
            log.error("the path is not right, path=" + filePath);
            return null;
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String temp = null;
            while((temp = br.readLine()) != null) {
                content.append(temp);
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            log.error("catch some exception", e);
        } catch (IOException e) {
            log.error("catch some exception", e);
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("catch some exception", e);
                }
            }
        }
        return null;
    }
}
