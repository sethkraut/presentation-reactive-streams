package com.sethkraut.streamvsflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

/**
 * Shows flux reuse
 */
public class Reuse {
    public static void main(String[] args) {
        // Reactive Streams
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        flux.map(n-> "Num " + n).consume(System.out::println);
        flux.count().map(n-> "Count " + n).consume(System.out::println);

        // Java 8 Streams
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.map(n->"Stream " + n).forEach(System.out::println);
        // This doesn't work
        stream.count();
    }
}
