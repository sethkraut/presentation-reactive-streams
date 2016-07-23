package com.sethkraut.sample;

import reactor.core.publisher.Flux;

/**
 * First Sample
 */
public class Sample1 {
    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.just(1,2,3,4,5);
        Flux<String> letters = Flux.just("A","B","C","D","E");

        System.out.println("Zip");
        numbers.zipWith(letters).consume(System.out::println);

        System.out.println("Concat");
        numbers.map(String::valueOf).concatWith(letters).consume(System.out::println);
    }
}
