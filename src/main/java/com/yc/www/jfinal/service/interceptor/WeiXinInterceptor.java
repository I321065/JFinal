package com.yc.www.jfinal.service.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.yc.www.jfinal.service.utils.WxSignUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Nick on 2017/4/22.
 */
public class WeiXinInterceptor implements Interceptor {
    // 验证请求来源拦截器
    Logger log = LogManager.getLogger(WeiXinInterceptor.class);

    public void intercept(Invocation in) {
        Controller c = in.getController();
        String methodType= c.getRequest().getMethod();

        log.info("----methodType ="+ methodType);
        if("GET".equals(methodType)){
            String signature = c.getPara("signature");
            // 时间戳
            String timestamp = c.getPara("timestamp");
            // 随机数
            String nonce = c.getPara("nonce");
            // 随机字符串
            String echostr = c.getPara("echostr");
            log.info("signature = " +signature +" ****timestamp = " + timestamp);
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            boolean chkFlag= WxSignUtil.checkSignature(signature, timestamp, nonce);
            log.info("-------SignUtil.checkSignature result=" + chkFlag);
            if (chkFlag) {
                try {
                    //modified by peter 2016-11-24
                    PrintWriter pw=c.getResponse().getWriter();
                    pw.print(echostr);
                    pw.close();
                    in.invoke();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else {
                log.error("validate wexin failed");
            }
        }else if("POST".equals(methodType)){
            try {
                // c.getResponse().getWriter().write(echostr);
                //modified by peter 2016-11-24
                PrintWriter pw=c.getResponse().getWriter();
                pw.print("success");
                pw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
