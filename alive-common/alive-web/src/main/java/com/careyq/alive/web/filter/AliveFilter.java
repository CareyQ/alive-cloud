package com.careyq.alive.web.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import com.careyq.alive.core.thread.AliveThreadContextHolder;
import com.careyq.alive.core.util.ServletUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * 服务过滤器
 *
 * @author CareyQ
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AliveFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 请求数据为json时进行body缓存
        String contentType = request.getHeader(ServletUtils.CONTENT_TYPE);
        if (StrUtil.isNotEmpty(contentType) && StrUtil.startWithIgnoreCase(contentType, ContentType.JSON.toString())) {
            request = new BodyHttpServletRequestWrapper(request);
        }

        AliveThreadContextHolder.setRequest(request);
        AliveThreadContextHolder.setResponse(response);

        filterChain.doFilter(request, response);
        AliveThreadContextHolder.clearAll();
    }
}
