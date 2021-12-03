package com.reactive;

import reactor.core.publisher.Flux;

/**
 * @author shashshe
 */
public class FluxGenerateSynchronous {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            String countryName = ReactiveUtil.FAKER().country().name();
            synchronousSink.next(countryName);
            //generate until Canada is populated
            if(countryName.equalsIgnoreCase("Canada")) synchronousSink.complete();
        }).subscribe(ReactiveUtil.onNext());

    }
}
