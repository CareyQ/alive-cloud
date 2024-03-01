package com.careyq.alive.satoken.rpc;

import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpUtil;
import feign.RequestTemplate;

/**
 * 请求拦截器，用于在 feign 调用的时候，将 token 放入请求头
 *
 * @author CareyQ
 */
public class RequestInterceptor implements feign.RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
        try {
            // 当存在web上下文的时候，就正常在请求里面塞进去登录的时候申请到的token
            requestTemplate.header(StpUtil.getTokenName(), StpUtil.getTokenValue());
        } catch (Exception e) {
            //在无web上下文的情况下，上面try里面的获取用户token的方法StpUtil.getTokenValue()会抛出错误，
            //这里将抛出异常视为无web上下文的情况。无web上下文的时候，token的值赋值为SaSameToken的值，
            //这个情况下，token的值可以通过自己的想法去赋值，不一定与我的想法一致。
            requestTemplate.header(StpUtil.getTokenName(), SaSameUtil.getToken());
        }

    }
}
