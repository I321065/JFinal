package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import javax.sql.DataSource;

/**
 * Created by Nick on 2017/4/23.
 */
public class initDataBase implements Interceptor {
    public void intercept(Invocation invocation) {
        Prop property = PropKit.use("jdbc.txt");
        C3p0Plugin c3p0Plugin = new C3p0Plugin(property.get("jdbcUrl"), property.get("user"), property.get("password").trim());
        DataSource dataSource = c3p0Plugin.getDataSource();

        // base model 所使用的包名
        String baseModelPackageName = "com.yc.www.jfinal.model.bean.base";

        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/com/yc/model/data/base";

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "cn.test.model.data";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";


    }
}
