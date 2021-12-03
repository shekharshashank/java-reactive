package com.reactive.batches;

import com.reactive.ReactiveUtil;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.function.Function;

/**
 * @author shashshe
 */
public class OrderGroupingService {

    public static void main(String[] args) {

        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> maps = Map.of(
                "Kids",OrderProcessor.kidsPurchaseOrder(),
                "Automotive",OrderProcessor.automotivePurchaseOrder()
        );

        OrderService.orderStream()
                .filter(p -> maps.containsKey(p.getCategory()))
                .groupBy(p -> p.getCategory())
                .flatMap(gf -> maps.get(gf.key()).apply(gf))
                .subscribe(ReactiveUtil.onNext(),ReactiveUtil.onError(),ReactiveUtil.onComplete());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
