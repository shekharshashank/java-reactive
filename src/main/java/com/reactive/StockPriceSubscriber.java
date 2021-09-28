package com.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

/**
 * @author shashshe
 */
public class StockPriceSubscriber {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        StockPricePublisher.getStockPrice().subscribeWith(new Subscriber<Integer>() {
            Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription =subscription;
                subscription.request(Long.MAX_VALUE);

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(LocalDateTime.now()+"::"+integer);
                if(integer>110 || integer < 80){
                    subscription.cancel();
                    countDownLatch.countDown();
                }

            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
                System.out.println("Error..."+throwable.getMessage());

            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
                System.out.println("completed...");

            }
        });

        countDownLatch.await();

    }
}
