package com.example.CacheServer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineHandler implements CommandLineRunner {

    private final CacheStore cacheStore;

    @Override
    public void run(String... args) {

        System.out.println("CommandLineHandler started");

        for (int i = 0; i < args.length; i++) {

            System.out.println("Argument: " + args[i]);

            if (args[i].equals("--port") && i + 1 < args.length) {
                System.out.println("Port: " + args[i + 1]);
            }

            if (args[i].equals("--origin") && i + 1 < args.length) {
                System.out.println("Origin: " + args[i + 1]);
            }

            if (args[i].equals("--clear-cache")) {
                cacheStore.clear();
                System.out.println("Cache cleared");
            }
        }
    }
}