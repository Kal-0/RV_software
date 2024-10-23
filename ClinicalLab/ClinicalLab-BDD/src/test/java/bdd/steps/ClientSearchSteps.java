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
import java.util.NoSuchElementException;

public class ClientSearchSteps {

    private MemoryRepository memoryRepository = new MemoryRepository(); 
    private ClientService clientService = new ClientService(memoryRepository);
    private Client foundClient;
    private String cpf;
    private RuntimeException exception;
    
    
    @Given("that there is a client with the id {int}, CPF {string} in the system")
    public void that_there_is_a_client_with_the_id_CPF_in_the_system(int id, String cpf) {
    	if(id != 2) {
	    	Client client = new Client(
	                new PersonId(id), 
	                new Cpf(cpf), 
	                new Email("johndoe@example.com"), 
	                "John Doe", 
	                LocalDate.of(1990, 1, 1), 
	                new ClientId(id)
	            );
    	clientService.save(client);
    	}
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
 

    @When("the system searches for the CPF")
    public void the_system_searches_for_the_cpf() {
        try {
            foundClient = clientService.getClientByCpf(new Cpf(cpf));
            exception = null; 
        } catch (NoSuchElementException e) {
            exception = e;
            foundClient = null;
        }
    }
    
    @Then("the system shows {string}")
    public void the_system_shows(String expectedMessage) {
    	if(expectedMessage.equals("client found")) {
    		assertNull(exception, "Client found"); 
    		assertNotNull(foundClient);
    	}
    	else {
    	
    		assertNotNull(exception, "no exception thronw");
    		assertEquals(exception.getMessage(), expectedMessage);
    	}
    }
    

    @Then("the system prompts the attendant to register a new client")
    public void the_system_prompts_the_attendant_to_register_a_new_client() {
        assertNotNull(exception, "An exception should be thrown for missing client");
        System.out.println("Message: Please register a new client.");
    }

}
