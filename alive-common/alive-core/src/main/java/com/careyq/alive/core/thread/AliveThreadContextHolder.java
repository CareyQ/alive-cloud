package com.careyq.alive.core.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求变量共享
 * 解决父子线程之间的变量传递
 *
 * @author CareyQ
 */
public class AliveThreadContextHolder {

    /**
     * HttpServerRequest
     */
    private static final TransmittableThreadLocal<HttpServletRequest> REQUEST_TTL = new TransmittableThreadLocal<>();
    /**
     * HttpServletResponse
     */
    private static final TransmittableThreadLocal<HttpServletResponse> RESPONSE_TTL = new TransmittableThreadLocal<>();

    /**
     * 清除所有
     */
    public static void clearAll() {
        REQUEST_TTL.remove();
        RESPONSE_TTL.remove();
    }

    /**
     * 设置 HttpServerRequest
     *
     * @param request HttpServerRequest
     */
    public static void setRequest(HttpServletRequest request) {
        REQUEST_TTL.set(request);
    }

    /**
     * 获取 HttpServerRequest
     *
     * @return HttpServerRequest
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = REQUEST_TTL.get();
        if (request == null && RequestContextHolder.getRequestAttributes() != null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        return request;
    }

    /**
     * 设置 HttpServletResponse
     *
     * @param response HttpServletResponse
     */
    public static void setResponse(HttpServletResponse response) {
        RESPONSE_TTL.set(response);
    }

    /**
     * 获取 HttpServletResponse
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = RESPONSE_TTL.get();
        if (response == null && RequestContextHolder.getRequestAttributes() != null) {
            response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        }
        return response;
    }
}
