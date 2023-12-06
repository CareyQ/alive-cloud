package com.careyq.alive.web.xss;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Xss 请求 wrapper
 *
 * @author CareyQ
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    private final JsoupXssCleaner jsoupXssCleaner;

    public XssRequestWrapper(HttpServletRequest request, JsoupXssCleaner jsoupXssCleaner) {
        super(request);
        this.jsoupXssCleaner = jsoupXssCleaner;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = new LinkedHashMap<>();
        Map<String, String[]> parameters = super.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String[] values = entry.getValue();
            for (int i = 0; i < values.length; i++) {
                values[i] = jsoupXssCleaner.clean(values[i]);
            }
            map.put(entry.getKey(), values);
        }
        return map;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = jsoupXssCleaner.clean(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value == null) {
            return null;
        }
        return jsoupXssCleaner.clean(value);
    }

    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(name);
        if (value instanceof String) {
            return jsoupXssCleaner.clean((String) value);
        }
        return value;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return jsoupXssCleaner.clean(value);
    }

    @Override
    public String getQueryString() {
        String value = super.getQueryString();
        if (value == null) {
            return null;
        }
        return jsoupXssCleaner.clean(value);
    }
}
