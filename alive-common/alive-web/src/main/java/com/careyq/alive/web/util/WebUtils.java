package com.careyq.alive.web.util;

import com.careyq.alive.core.enums.UserTypeEnum;
import com.careyq.alive.web.config.WebProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * web 工具类
 *
 * @author CareyQ
 */
public class WebUtils {

    private static final String REQUEST_ATTRIBUTE_LOGIN_USER_TYPE = "login_user_type";

    private static WebProperties properties;

    public WebUtils(WebProperties webProperties) {
        WebUtils.properties = webProperties;
    }

    /**
     * 获取请求 HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取登录用户类型
     *
     * @return 用户类型
     */
    public static Integer getLoginUserType() {
        return getLoginUserType(getRequest());
    }

    /**
     * 获取登录用户类型
     *
     * @param request 请求
     * @return 用户类型
     */
    public static Integer getLoginUserType(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        // 1. 优先，从 Attribute 中获取
        Integer userType = (Integer) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_TYPE);
        if (userType != null) {
            return userType;
        }
        // 2. 其次，基于 URL 前缀的约定
        if (request.getServletPath().startsWith(properties.getAdminApi().getPrefix())) {
            return UserTypeEnum.ADMIN.getType();
        }
        return null;
    }

    /**
     * 判断是否为 RPC 请求，XxxApi 都为 RPC
     *
     * @param className 类名
     * @return 是否为 RPC 请求
     */
    public static boolean isRpcRequest(String className) {
        return className.endsWith("Api");
    }
}
