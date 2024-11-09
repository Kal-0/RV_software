package infrastructure;

import static org.springframework.boot.SpringApplication.run;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import infrastructure.decoy.DecoyPersistence3;

@SpringBootApplication
public class Aplicacao {
	

	public static void main(String[] args) throws IOException {
		run(Aplicacao.class, args);
		
		 DecoyPersistence3.main(args);
	}
}