package com.careyq.alive.web.xss.filter;

import com.careyq.alive.web.xss.JsoupXssCleaner;
import com.careyq.alive.web.xss.XssRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Xss 过滤器
 *
 * @author CareyQ
 * @since 2023-11-17
 */
@AllArgsConstructor
public class XssFilter extends OncePerRequestFilter {

    private final JsoupXssCleaner jsoupXssCleaner;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(new XssRequestWrapper(request, jsoupXssCleaner), response);
    }

}
