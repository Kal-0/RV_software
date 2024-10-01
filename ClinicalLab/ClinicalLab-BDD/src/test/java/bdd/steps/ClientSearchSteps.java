package bdd.steps;

import domain.common.entities.client.Client;
import domain.common.entities.client.ClientId;
import domain.common.entities.person.Cpf;
import domain.common.entities.person.Email;
import domain.common.entities.person.PersonId;
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

    @Given("a registered client with CPF {string} exists in the system")
    public void a_registered_client_with_cpf_exists_in_the_system(String cpf) {
        this.cpf = cpf; 
        ClientId clientId = new ClientId(1); 
        Email contactEmail = new Email("example@example.com");
        String name = "John Doe";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        
        Client client = new Client(new PersonId(1), new Cpf(cpf), contactEmail, name, birthDate, clientId);
        memoryRepository.save(client); 
        System.out.println("Client with CPF " + cpf + " has been registered.");
    }

    @Given("the attendant enters the client's CPF {string} in the system")
    public void the_attendant_enters_the_client_s_cpf_in_the_system(String cpf) {
        this.cpf = cpf; 
        System.out.println("Attendant enters CPF: " + cpf);
    }

    @Given("the attendant enters a CPF {string} in the system")
    public void the_attendant_enters_a_cpf_in_the_system(String cpf) {
        this.cpf = cpf; 
        System.out.println("Attendant enters CPF: " + cpf);
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

    @Then("the system shows a message stating that the client is already registered")
    public void the_system_shows_a_message_stating_that_the_client_is_already_registered() {
        assertNotNull(foundClient, "Client should be found"); 
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
