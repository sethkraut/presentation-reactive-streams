package com.sethkraut.streamvsflux;

import reactor.core.publisher.Flux;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Error handling between Streams and Flux
 */
public class ErrorHandling {
    private final int start;
    private final int end;

    public ErrorHandling(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Stream<Integer> stream() {
        return IntStream.rangeClosed(start, end).boxed();
    }

    public Flux<Integer> flux() {
        return Flux.fromStream(this.stream());
    }

    public static void main(String[] args) {
        ErrorHandling nums = new ErrorHandling(0,5);

        try {
            nums.stream().map(n -> 1/n).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("On error");
        nums.flux().map(n -> 1/n).onErrorReturn(0).consume(System.out::println);

        System.out.println("Resume with");
        nums.flux().map(n -> 1/n).onErrorResumeWith(t -> Flux.just(1,2,3)).map(s -> "The value is" + s).partition();
    }
}
