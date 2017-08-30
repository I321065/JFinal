package com.yc.www.jfinal.controller.weixin;

import com.jfinal.aop.Before;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.yc.www.jfinal.service.interceptor.WeiXinInterceptor;

/**
 * Created by Nick on 2017/4/22.
 */

@Before(WeiXinInterceptor.class)
public class WxController extends MsgControllerAdapter {

    protected void processInFollowEvent(InFollowEvent inFollowEvent) {

    }

    public ApiConfig getApiConfig() {
        return null;
    }

    protected void processInTextMsg(InTextMsg inTextMsg) {

    }

    protected void processInMenuEvent(InMenuEvent inMenuEvent) {

    }
}
