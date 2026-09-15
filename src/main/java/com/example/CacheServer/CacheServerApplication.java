package com.example.CacheServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheServerApplication {

	public static void main(String[] args) {

		for (int i = 0; i < args.length; i++) {

			if (args[i].equals("--port")) {

				// Check whether port value exists
				if (i + 1 >= args.length) {
					System.out.println("Error: --port requires a port number.");
					return;
				}

				String port = args[i + 1];

				// Check whether port is a number
				int portNumber;

				try {
					portNumber = Integer.parseInt(port);
				} catch (NumberFormatException e) {
					System.out.println("Error: Invalid port number: " + port);
					return;
				}

				// Check valid port range
				if (portNumber < 1 || portNumber > 65535) {
					System.out.println(
							"Error: Port must be between 1 and 65535."
					);
					return;
				}

				// Convert custom argument to Spring Boot argument
				args[i] = "--server.port=" + portNumber;
				args[i + 1] = "";
			}
		}

		SpringApplication.run(CacheServerApplication.class, args);
	}

}
