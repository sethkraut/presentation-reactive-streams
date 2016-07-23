package com.sethkraut.endtoend;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class UserRepository {
    private final static String[] FIRST_NAMES = {"Michael","Allison","Joseph","Bea"};
    private final static String[] LAST_NAMES = {"Smith", "Johnson","Green", "Howard"};

    private List<User> data = new ArrayList<>();

    public UserRepository() {
        data = Flux.just(FIRST_NAMES)
                .zipWith(Flux.just(LAST_NAMES))
                .map(t -> User.withName(t.getT1(), t.getT2()))
                .collect(Collectors.toList())
                .get();
    }

    public Flux<User> findAllUsers() {
        return Flux.fromIterable(data).delay(Duration.ofMillis(500));
    }

    public Mono<User> findUser(String username) {
        return findAllUsers().filter(u -> u.getUsername().equals(username)).single();
    }
}
