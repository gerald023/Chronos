package com.Chronos.models;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class TimeUpdateEmitter extends SseEmitter {
    public TimeUpdateEmitter() {
        super();
    }

    public TimeUpdateEmitter(long timeout) {
        super(timeout);
    }

}
