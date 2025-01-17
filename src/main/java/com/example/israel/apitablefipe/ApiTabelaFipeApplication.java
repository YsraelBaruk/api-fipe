package com.example.israel.apitablefipe;

import com.example.israel.apitablefipe.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiTabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiTabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.getMenu();
	}
}
