package com.careyq.alive.satoken.core.service;

import cn.dev33.satoken.context.SaTokenContext;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.servlet.model.SaRequestForServlet;
import cn.dev33.satoken.servlet.model.SaResponseForServlet;
import cn.dev33.satoken.servlet.model.SaStorageForServlet;
import cn.dev33.satoken.spring.pathmatch.SaPathPatternParserUtil;
import com.careyq.alive.core.util.ServletUtils;

/**
 * Sa-Token 上下文实现类
 *
 * @author CareyQ
 */
public class SaTokenContextImpl implements SaTokenContext {

    @Override
    public SaRequest getRequest() {
        return new SaRequestForServlet(ServletUtils.getRequest());
    }

    @Override
    public SaResponse getResponse() {
        return new SaResponseForServlet(ServletUtils.getResponse());
    }

    @Override
    public SaStorage getStorage() {
        return new SaStorageForServlet(ServletUtils.getRequest());
    }

    @Override
    public boolean matchPath(String pattern, String path) {
        return SaPathPatternParserUtil.match(pattern, path);
    }

    @Override
    public boolean isValid() {
        return ServletUtils.isWeb();
    }
}
