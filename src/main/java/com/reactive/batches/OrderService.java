package com.reactive.batches;

import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author shashshe
 */
public class OrderService {
    public static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder());
    }
}
