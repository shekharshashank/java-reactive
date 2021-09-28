package com.reactive;

import com.github.javafaker.Faker;

import java.util.function.Consumer;

/**
 * @author shashshe
 */
public class ReactiveUtil {
    public static Consumer<Object> onNext() {
        return o -> System.out.println("Recieved " + o);
    }

    public static Consumer<Throwable> onError() {
        return err -> System.out.println("Error " + err.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed ");
    }

    public static Faker FAKER() {
        return Faker.instance();
    }
}
