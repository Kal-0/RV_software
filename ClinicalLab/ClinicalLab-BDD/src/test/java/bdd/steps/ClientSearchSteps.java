package bdd.steps;

import domain.common.entities.client.Client;
import domain.common.entities.client.ClientId;
import domain.common.entities.person.Cpf;
import domain.common.entities.person.Email;
import domain.common.entities.person.PersonId;
import domain.service.ClientService;
import infrastructure.persistence.memory.MemoryRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class ClientSearchSteps {

    private MemoryRepository memoryRepository = new MemoryRepository(); 
    private ClientService clientService = new ClientService(memoryRepository);
    private Client foundClient;
    private String cpf;
    private RuntimeException exception;
    
    
    @Given("that there is a client with the CPF {string} in the system")
    public void that_there_is_a_client_with_the_CPF_in_the_system(String cpf) {
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
    
    @Given("the attendant enters the client's CPF {string} in the system")
    public void the_attendant_enters_the_client_s_cpf_in_the_system(String cpf) {
        this.cpf = cpf;
    }

    @When("the system searches for the client")
    public void the_system_searches_for_the_client() {
        try {
        	
            foundClient = clientService.getClientByCpf(new Cpf(cpf));
            exception = null; 
        } catch (NoSuchElementException e) {
            exception = e;
            foundClient = null;
        }
    } 

    @Then("the system returns the client's information")
    public void the_system_returns_the_clients_information() {
    	System.out.println("Client found: " + foundClient);
        assertNotNull(foundClient, "Client should be found");
    }
 

    @Then("the system shows a message stating that the client is already registered")
    public void the_system_shows_a_message_stating_that_the_client_is_already_registered() {
        assertNotNull(foundClient, "Client should be found"); // Ensure client is found
        assertNull(exception, "No exception should be thrown if the client exists");
        System.out.println("Message: Client is already registered");
    }

    @Then("the system returns a message stating that no client was found")
    public void the_system_returns_a_message_stating_that_no_client_was_found() {
        assertNotNull(exception, "Exception should be thrown if no client is found");
        assertEquals(NoSuchElementException.class, exception.getClass(), "Should throw NoSuchElementException");
        System.out.println("Message: No client was found");
    }

    @Then("the system prompts the attendant to register a new client")
    public void the_system_prompts_the_attendant_to_register_a_new_client() {
        assertNotNull(exception, "An exception should be thrown for missing client");
        System.out.println("Message: Please register a new client.");
    }

}
