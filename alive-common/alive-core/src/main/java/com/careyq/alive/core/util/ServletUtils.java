package com.careyq.alive.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.careyq.alive.core.thread.AliveThreadContextHolder;
import com.careyq.alive.core.thread.BodyHttpServletRequestWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * ServletUtil 扩展
 *
 * @author CareyQ
 */
@Slf4j
public class ServletUtils extends JakartaServletUtil {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String UNKNOWN = "未知";
    public static final String IP_QUERY_RUL = "https://whois.pconline.com.cn/ipJson.jsp?json=true&ip=";
    private static final String FORMAT_HEADER = "-H '%1$s:%2$s'";
    private static final String FORMAT_METHOD = "-X %1$s";
    private static final String FORMAT_BODY = "-d '%1$s'";
    private static final String FORMAT_URL = "'%1$s'";

    public static HttpServletRequest getRequest() {
        return AliveThreadContextHolder.getRequest();
    }

    public static HttpServletResponse getResponse() {
        return AliveThreadContextHolder.getResponse();
    }

    public static boolean isWeb() {
        if (ServletUtils.getRequest() == null) {
            return RequestContextHolder.getRequestAttributes() != null;
        }
        return true;
    }

    /**
     * 获取 IP
     *
     * @return IP 地址
     */
    public static String getClientIp() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return UNKNOWN;
        }
        return JakartaServletUtil.getClientIP(request);
    }

    /**
     * 获取 IP
     *
     * @return IP 地址
     */
    public static String getClientIp(HttpServletRequest request) {
        return JakartaServletUtil.getClientIP(request);
    }

    public static String getIpInfo(String ip) {
        if (StrUtil.isBlank(ip)) {
            return UNKNOWN;
        }

        String url = IP_QUERY_RUL + ip;
        try {
            String json = HttpUtil.get(url, 3000);
            JsonNode jsonNode = JsonUtils.parseObject(json);
            assert jsonNode != null;
            JsonNode addr = jsonNode.get("addr");
            if (!StrUtil.isEmptyIfStr(addr)) {
                return addr.asText();
            }
            JsonNode pro = jsonNode.get("pro");
            JsonNode city = jsonNode.get("city");
            if (!StrUtil.isEmptyIfStr(pro) && !StrUtil.isEmptyIfStr(city)) {
                return pro.asText() + " " + city.asText();
            }
        } catch (Exception e) {
            log.info("获取IP地理信息失败");
        }
        return UNKNOWN;
    }

    /**
     * 获取设备信息
     *
     * @return 设备信息
     */
    public static String getUserAgentInfo() {
        return getUserAgentInfo(getRequest());
    }

    /**
     * 获取设备信息
     *
     * @return 设备信息
     */
    public static String getUserAgentInfo(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }

        UserAgent ua = UserAgentUtil.parse(request.getHeader("user-agent"));
        if (ua == null) {
            return UNKNOWN;
        }

        String isMobile = ua.isMobile() ? "移动端" : "PC端";
        return ua.getBrowser().toString() + " " + ua.getVersion() + " | " + ua.getPlatform().toString()
                + " " + ua.getOs().toString() + " | " + isMobile;
    }

    /**
     * 获取请求 curl
     *
     * @param request HttpServletRequest
     * @return curl
     */
    public static String getCurl(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String curl;
        try {
            List<String> parts = new ArrayList<>();
            parts.add("curl");
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String contentType = request.getContentType();
            String queryString = request.getQueryString();
            parts.add(String.format(FORMAT_METHOD, method.toUpperCase()));

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                parts.add(String.format(FORMAT_HEADER, key, value));
            }
            if (contentType != null && !contentType.contains(CONTENT_TYPE)) {
                parts.add(String.format(FORMAT_HEADER, CONTENT_TYPE, contentType));
            }
            if (queryString != null) {
                url = HttpUtil.urlWithForm(url, queryString, CharsetUtil.CHARSET_UTF_8, false);
            }
            if (ContentType.isFormUrlEncode(contentType) && CollUtil.isNotEmpty(request.getParameterMap())) {
                request.getParameterMap().forEach((k, v) ->
                        parts.add(StrUtil.format("--data-urlencode '{}={}'", k, ArrayUtil.get(v, 0))));
            }
            if (StrUtil.startWithIgnoreCase(contentType, ContentType.JSON.toString()) && request instanceof BodyHttpServletRequestWrapper wrapper) {
                String body = StrUtil.utf8Str(wrapper.getCachedBody());
                if (StrUtil.isNotEmpty(body)) {
                    parts.add(String.format(FORMAT_BODY, body));
                }
            }
            parts.add(String.format(FORMAT_URL, url));
            curl = StrUtil.join(" ", parts);
        } catch (Exception ignored) {
            curl = null;
        }
        return curl;
    }
}
