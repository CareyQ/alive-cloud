package com.careyq.alive.core.util;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * @author CareyQ
 */
public class TraceUtils {

    public static String getTraceId() {
        return TraceContext.traceId();
    }
}
