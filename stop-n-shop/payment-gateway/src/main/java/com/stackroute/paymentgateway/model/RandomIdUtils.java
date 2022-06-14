package com.stackroute.paymentgateway.model;

import java.util.concurrent.atomic.AtomicReference;

public class RandomIdUtils {
    public static Long nextId() {
        return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1)
                % 10000000000L;
    }

    private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());
}