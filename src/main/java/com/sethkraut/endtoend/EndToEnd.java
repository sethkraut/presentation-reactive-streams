package com.sethkraut.endtoend;

import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;
import ratpack.websocket.WebSockets;
import reactor.core.publisher.Mono;

/**
 * End to end reactive programming
 */
public class EndToEnd {
    public static void main(String[] args) throws Exception {
        UserRepository repository = new UserRepository();

        RatpackServer.start(server -> server
                .serverConfig(ServerConfig.builder().build())
                .handlers(chain -> {
                    chain.get("user", context -> {
                        WebSockets.websocketBroadcast(context, repository.findAllUsers().map(User::getUsername));
                    });

                    chain.get("user/:username", context -> {
                        WebSockets.websocketBroadcast(context, repository.findUser(context.getPathTokens().get("username"))
                                .map(u -> u.getFirstName() + " " + u.getLastName())
                                .otherwise(t-> Mono.just("User not found"))
                        );

                    });
                }));

        System.out.println("Access at ws://localhost:5050/user");
    }
}
