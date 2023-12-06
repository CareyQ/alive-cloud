package com.careyq.alive.core.util;

import com.navercorp.pinpoint.bootstrap.context.Trace;
import com.navercorp.pinpoint.bootstrap.context.TraceContext;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor0;
import com.navercorp.pinpoint.sdk.v1.concurrent.TraceCallable;

/**
 * @author CareyQ
 */
public class TraceUtils implements AroundInterceptor0 {

    private final TraceContext traceContext;

    public TraceUtils(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    public static String getTraceId() {
        TraceCallable
        Trace trace = traceContext.currentTraceObject();
    }
}
