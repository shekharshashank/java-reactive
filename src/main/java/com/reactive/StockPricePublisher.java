package com.reactive;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shashshe
 */
public class StockPricePublisher {

    static AtomicInteger atomicInteger = new AtomicInteger(100);

    public static void main(String[] args) {

    }

    public static Flux<Integer> getStockPrice() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> atomicInteger.accumulateAndGet(ReactiveUtil.FAKER().random().nextInt(-5, 5), (a, b) -> a + b));

    }
}
