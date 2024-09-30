package bdd.steps;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import domain.common.entities.client.Client;
import domain.common.entities.client.ClientId;
import domain.common.entities.person.Cpf;
import domain.common.entities.person.Email;
import domain.common.entities.person.PersonId;
import infrastructure.persistence.memory.MemoryRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ClientRegisterSteps {
    private MemoryRepository memoryRepository = new MemoryRepository();
    private Client client;
    private RuntimeException exception;

    @Given("a {string} client with {string} data")
    public void a_client_with_data(String clientType, String dataType) {
        try {
            if (clientType.equals("new") && dataType.equals("correct")) {
                client = new Client(
                    new PersonId(1), 
                    new Cpf("12345678900"), 
                    new Email("johndoe@example.com"), 
                    "John Doe", 
                    LocalDate.of(1990, 1, 1), 
                    new ClientId(1)
                );
            } else if (clientType.equals("new") && dataType.equals("incorrect")) {
                client = new Client(
                    new PersonId(1), 
                    new Cpf(""), 
                    new Email(""), 
                    "", 
                    LocalDate.of(1990, 1, 1), 
                    new ClientId(1)
                );
            } else if (clientType.equals("returning") && dataType.equals("correct")) {
                memoryRepository.save(new Client(
                    new PersonId(1), 
                    new Cpf("12345678900"), 
                    new Email("johndoe@example.com"), 
                    "John Doe", 
                    LocalDate.of(1990, 1, 1), 
                    new ClientId(1)
                ));
                client = new Client(
                    new PersonId(1), 
                    new Cpf("12345678900"), 
                    new Email("johndoe@example.com"), 
                    "John Doe", 
                    LocalDate.of(1990, 1, 1), 
                    new ClientId(1)
                );
            }
        } catch (RuntimeException e) {
            exception = e;
        }
    }

    @When("an attendant registers the client")
    public void an_attendant_registers_the_client() {
        try {
            memoryRepository.save(client);
        } catch (RuntimeException e) {
            exception = e;
            System.out.println(exception);
        }
    }

    @Then("the system registers the client successfully")
    public void the_system_registers_the_client_successfully() {
        assertNull(exception);
    }

    @Then("the system notifies that the client is registered")
    public void the_system_notify_the_register_of_the_client() {
        assertNull(exception);
    }

    @Then("the system returns an error message informing the incorrect data")
    public void the_system_returns_an_error_message_informing_the_incorrect_data() {
        assertNotNull(exception);
    }

    @Then("the system returns an error message stating the client is already registered")
    public void the_system_returns_an_error_message_informing_that_the_client_is_already_registered() {
        assertNotNull(exception);
    }
}
