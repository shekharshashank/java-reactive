package com.reactive.batches;

import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @author shashshe
 */
public class OrderProcessor {

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotivePurchaseOrder(){

        return flux -> flux
                .doOnNext(purchaseOrder -> purchaseOrder.setPrice(purchaseOrder.getPrice()*1.1))
                .doOnNext(purchaseOrder -> purchaseOrder.setItem("{{ "+purchaseOrder.getItem()+" }}"));

    }

    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsPurchaseOrder(){
        return  flux -> flux
                .doOnNext(purchaseOrder -> purchaseOrder.setPrice(purchaseOrder.getPrice()*0.5))
                .flatMap(purchaseOrder -> Flux.just(purchaseOrder, getFreeProduct()));

    }

    private static PurchaseOrder getFreeProduct(){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPrice(0);
        purchaseOrder.setItem("FREE=="+purchaseOrder.getItem());
        purchaseOrder.setCategory("Kids");
        return purchaseOrder;
    }
}
