package infrastructure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import infrastructure.decoy.DecoyPersistence4;

@SpringBootApplication
public class Aplicacao {

    public static void main(String[] args) {
        SpringApplication.run(Aplicacao.class, args);
    }

    // Configura um bean para executar DecoyPersistence4 após a inicialização da aplicação
    @Bean
    CommandLineRunner run(DecoyPersistence4 decoyPersistence4) {
        return args -> {
            decoyPersistence4.executeTest();
        };
    }
}
