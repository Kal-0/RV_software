package cesar.rv.clinicallabdocker;

import static org.springframework.boot.SpringApplication.run;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import domain.entities.client.ClientRepository;
import domain.services.ClientService;
import domain.entities.clientservice.ClientServiceRepository;
import domain.services.ClientServiceService;
import domain.entities.examrequest.ExamRequestRepository;
import domain.services.ExamRequestService;
import domain.services.TotalPriceService;
import domain.entities.examtest.ExamTestRepository;
import domain.entities.exam.ExamRepository;


@SpringBootApplication
public class Application {

    @Bean
    public ClientService clientService(ClientRepository repository) {
        return new ClientService(repository);
    }

    @Bean
    public ClientServiceService clientServiceService(ClientServiceRepository repository) {
        return new ClientServiceService(repository);
    }

    @Bean
    public TotalPriceService totalPriceService(ExamTestRepository examTestRepository, ExamRepository examRepository) {
        return new TotalPriceService(examTestRepository, examRepository);
    }

    @Bean
    public ExamRequestService examRequestService(ExamRequestRepository repository, TotalPriceService totalPriceService) {
        return new ExamRequestService(repository, totalPriceService);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner inspectBeans(ApplicationContext ctx) {
        return args -> {
            System.out.println("Beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            for (String beanName : beanNames) {
                if (beanName.contains("domain")) { // Filtrar apenas beans do backend
                    System.out.println(beanName);
                }
            }
        };
    }
}
