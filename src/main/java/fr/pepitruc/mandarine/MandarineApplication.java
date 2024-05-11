package fr.pepitruc.mandarine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class MandarineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MandarineApplication.class, args);
	}

}
