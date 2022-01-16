package com.example.spring.actuator.gettingstarted.httptrace;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Hint: Alternatively, use this trace repository and not the {@link HttpTraceConfig#httpTraceRepository()}.
 */

// @Repository
public class CustomTraceRepository implements HttpTraceRepository
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    // ============================== [Spring Beans] ==============================

    // -------------------- [Public Spring Beans] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    @Override
    public List<HttpTrace> findAll()
    {
        return Collections.singletonList(lastTrace.get());
    }

    @Override
    public void add(HttpTrace trace)
    {
        if ("GET".equals(trace.getRequest().getMethod()))
        {
            lastTrace.set(trace);
        }
    }
}
