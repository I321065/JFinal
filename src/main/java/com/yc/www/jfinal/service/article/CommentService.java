package com.yc.www.jfinal.service.article;

import com.yc.www.jfinal.service.article.bean.Comment;
import com.yc.www.jfinal.service.utils.SaveToDBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by I321065 on 9/25/2017.
 */
public class CommentService {

    private static final Logger log = LogManager.getLogger(CommentService.class);

    public Comment save(Comment comment) {
        return SaveToDBUtil.saveModel(comment);
    }


}
