package com.sethkraut.sample;

import reactor.core.publisher.Flux;

/**
 * Mono Samples
 */
public class MonoSamples {
    public static void main(String[] args) {
        Flux.just(1,2,3,4,5).after().consume(v -> System.out.println("All done."));
    }
}
