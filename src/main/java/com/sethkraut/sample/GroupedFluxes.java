package com.sethkraut.sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;

import java.util.stream.IntStream;

import static com.sethkraut.sample.GroupedFluxes.EvenOrOdd.EVEN;
import static com.sethkraut.sample.GroupedFluxes.EvenOrOdd.ODD;

/**
 *
 */
public class GroupedFluxes {
    enum EvenOrOdd {
        EVEN,
        ODD
    }

    private static EvenOrOdd evenOrOdd(Integer i) {
        return (i%2 == 0) ? EVEN : ODD;
    }

    public static void main(String[] args) {
        Flux<GroupedFlux<EvenOrOdd, Integer>> grouped = Flux.fromStream(IntStream.range(0, 100).boxed())
                .groupBy(GroupedFluxes::evenOrOdd);

        grouped
                .flatMap(g -> g.count().map(c -> g.key() + " " + c.intValue()))
                .consume(System.out::println);
    }
}
