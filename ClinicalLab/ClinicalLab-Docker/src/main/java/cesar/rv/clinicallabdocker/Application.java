package cesar.rv.clinicallabdocker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import domain.entities.client.ClientRepository;
import domain.services.ClientService;
import domain.services.ServiceNumberService;
import domain.entities.servicenumber.ServiceNumberRepository;
/*import domain.entities.clientservice.ClientServiceRepository;
import domain.services.ClientServiceService; */
import domain.entities.examrequest.ExamRequestRepository;
import domain.services.ExamRequestService;
import domain.services.TotalPriceService;
import domain.entities.examtest.ExamTestRepository;
import domain.entities.exam.ExamRepository;


@SpringBootApplication(scanBasePackages = {
	    "pai",
		"cesar.rv.clinicallabdocker",
	    "infrastructure.persistence",
	    "backend.controllers"
	})
@ComponentScan(basePackages = {
		"pai",
	    "cesar.rv.clinicallabdocker",
	    "infrastructure.persistence",
	    "backend.controllers"
	})
@EnableJpaRepositories(basePackages = "infrastructure.persistence.jpa.repository")
@EntityScan(basePackages = "infrastructure.persistence.jpa")
public class Application {

    @Bean
    public ClientService clientService(ClientRepository repository) {
        return new ClientService(repository);
    }

//    @Bean
//    public ClientServiceService clientServiceService(ClientServiceRepository repository) {
//        return new ClientServiceService(repository);
//    }
//
    @Bean
    public TotalPriceService totalPriceService(ExamTestRepository examTestRepository, ExamRepository examRepository) {
        return new TotalPriceService(examTestRepository, examRepository);
    }

    @Bean
    public ExamRequestService examRequestService(ExamRequestRepository repository, TotalPriceService totalPriceService) {
        return new ExamRequestService(repository, totalPriceService);
    }
    
    @Bean
    public ServiceNumberService serviceNumberService(ServiceNumberRepository repository) {
        return new ServiceNumberService(repository);
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
//            	System.out.println(beanName);
                if (beanName.contains("domain")) { // Filtrar apenas beans do backend
                    System.out.println(beanName);
                    
                }
            }
        };
    }
}
