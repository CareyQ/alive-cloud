package com.careyq.alive.web.xss;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Xss 过滤器
 *
 * @author CareyQ
 */
@AllArgsConstructor
public class XssFilter extends OncePerRequestFilter {

    private final JsoupXssCleaner jsoupXssCleaner;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(new XssRequestWrapper(request, jsoupXssCleaner), response);
    }

}
