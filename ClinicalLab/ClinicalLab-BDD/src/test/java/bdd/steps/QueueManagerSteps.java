package bdd.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import domain.entities.clientservice.ClientServiceId;
import domain.entities.clientservice.ClientServices;
import domain.entities.clientservice.QueueManager;
import domain.entities.servicenumber.ServiceNumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QueueManagerSteps {

    private QueueManager queueManager;
    private ClientServices nextClientService;
    private String queueStatus;

    @Given("two {string} service numbers and one {string} service number in the queue")
    public void two_priority_and_one_standard_service_numbers_in_the_queue(String priority, String standard) {
        queueManager = new QueueManager();
        ClientServices priority1 = new ClientServices(new ClientServiceId(1), new ServiceNumber("P001", true)); // Prioritário
        ClientServices priority2 = new ClientServices(new ClientServiceId(2), new ServiceNumber("P002", true)); // Prioritário
        ClientServices standard1 = new ClientServices(new ClientServiceId(3), new ServiceNumber("S001", false)); // Padrão
        queueManager.addToQueue(priority1);
        queueManager.addToQueue(priority2);
        queueManager.addToQueue(standard1);
    }

    @Given("two {string} service numbers in the queue")
    public void two_priority_or_standard_service_numbers_in_the_queue(String type) {
        queueManager = new QueueManager();
        boolean isPriority = type.equals("priority");
        ClientServices service1 = new ClientServices(new ClientServiceId(1), new ServiceNumber("P001", isPriority));
        ClientServices service2 = new ClientServices(new ClientServiceId(2), new ServiceNumber("P002", isPriority));
        queueManager.addToQueue(service1);
        queueManager.addToQueue(service2);
    }


    @Given("one {string} service number and three {string} service numbers in the queue")
    public void one_priority_and_three_standard_service_numbers_in_the_queue(String priority, String standard) {
        queueManager = new QueueManager();
        ClientServices priority1 = new ClientServices(new ClientServiceId(1), new ServiceNumber("P001", true)); // Prioritário
        ClientServices standard1 = new ClientServices(new ClientServiceId(2), new ServiceNumber("S001", false)); // Padrão
        ClientServices standard2 = new ClientServices(new ClientServiceId(3), new ServiceNumber("S002", false)); // Padrão
        ClientServices standard3 = new ClientServices(new ClientServiceId(4), new ServiceNumber("S003", false)); // Padrão
        queueManager.addToQueue(priority1);
        queueManager.addToQueue(standard1);
        queueManager.addToQueue(standard2);
        queueManager.addToQueue(standard3);
    }
    
    @Given("the queue is empty")
    public void the_queue_is_empty() {
    	queueManager = new QueueManager();
    }

    @When("the attendant requests the next number")
    public void the_attendant_requests_the_next_number() {
        nextClientService = queueManager.callNextNumber();
    }
    
    @When("a new {string} service number is created")
    public void a_new_priority_service_number_is_created(String priority) {
        ClientServices newPriority = new ClientServices(new ClientServiceId(5), new ServiceNumber("P003", true)); // Novo Prioritário
        queueManager.addToQueue(newPriority);
    }

    @Then("the service number called is a {string} in order of arrival")
    public void the_service_number_called_is_in_order_of_arrival(String type) {
        assertNotNull(nextClientService);
        boolean isPriority = type.equals("priority");
        assertEquals(isPriority, nextClientService.getServiceNumber().isPriority());
    }

    @Then("the system returns a message indicating that there are no service numbers in the queue")
    public void the_system_returns_a_message_indicating_no_numbers() {
        assertNull(nextClientService);
        queueStatus = queueManager.getQueueStatus();
        assertEquals("Nenhum número de atendimento disponível no momento.", queueStatus);
    }

    
}
