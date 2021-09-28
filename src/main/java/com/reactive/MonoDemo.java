package com.reactive;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

/**
 * @author shashshe
 */
public class MonoDemo {
//    public static final Faker FAKER = MonoDemo.FAKER.ins

    public static void main(String[] args) {
        Faker faker = new Faker();
        System.out.println(faker.address().city());

        Mono<Integer> mono = Mono.just(1/1);
        mono.subscribe(ReactiveUtil.onNext(),ReactiveUtil.onError(),ReactiveUtil.onComplete());


    }
}
