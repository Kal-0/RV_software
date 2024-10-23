package bdd.steps;

import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.person.Cpf;
import domain.entities.person.Email;
import domain.entities.person.PersonId;
import domain.services.ClientService;
import infrastructure.persistence.memory.MemoryRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ClienteUpdateRegisterSteps {

    private MemoryRepository memoryRepository = new MemoryRepository(); 
    private ClientService clientService = new ClientService(memoryRepository);
    private Client foundClient;
    private String cpf;
    private String email;
    private String systemMessage;
    
    
    @Given("there is a client with ID {int} and CPF {string} in the system")
    public void that_there_is_a_client_with_the_CPF_in_the_system(Integer id, String cpf) {
    	Client client = new Client(
                new PersonId(1), 
                new Cpf(cpf), 
                new Email("johndoe@example.com"), 
                "John Doe", 
                LocalDate.of(1990, 1, 1), 
                new ClientId(1)
            );
    	
    	clientService.save(client);
    }
    
    @Given("the attendant searches for the client by CPF {string}")
    public void the_attendant_searches_for_the_client_by_CPF(String cpf) {
        this.cpf = cpf;
        try {
            foundClient = clientService.getClientByCpf(new Cpf(cpf));
        } catch (Exception e) {
            fail("Client with CPF " + cpf + " not found.");
        }
    }

    @When("the attendant updates the client's email to {string}")
    public void the_attendant_updates_the_client_s_email_to(String email) {
        this.email = email;
        if (foundClient != null) {
            foundClient.setContactEmail(new Email(email));
            clientService.updateClient(foundClient);
            systemMessage = "The client information is updated.";
        }
    }

    @Then("the system should display the message {string}")
    public void the_system_should_display_the_message(String expectedMessage) {
        assertEquals(expectedMessage, systemMessage, "The system message should match.");
    }
}
