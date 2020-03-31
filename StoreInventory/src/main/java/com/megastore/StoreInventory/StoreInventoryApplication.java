package com.megastore.StoreInventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StoreInventoryApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StoreInventoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started");
	}
}
