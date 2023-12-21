package com.careyq.alive.web.filter;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * body 缓存包装器
 *
 * @author CareyQ
 */
public class BodyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] cachedBody;

    public BodyHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.cachedBody = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedBodyServletInputStream(this.cachedBody);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 获取缓存的 body
     *
     * @return body
     */
    public byte[] getCachedBody() {
        return cachedBody;
    }
}
