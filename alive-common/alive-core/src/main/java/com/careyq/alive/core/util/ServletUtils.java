package com.careyq.alive.core.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ServletUtil 扩展
 *
 * @author CareyQ
 */
@Slf4j
public class ServletUtils extends JakartaServletUtil {

    public static final String UNKNOWN = "未知";
    public static final String IP_QUERY_RUL = "https://whois.pconline.com.cn/ipJson.jsp?json=true&ip=";

    public static HttpServletRequest getRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        return ((ServletRequestAttributes) attributes).getRequest();
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
}
