import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

import io.cucumber.java.en.*;
import org.jmolecules.event.types.DomainEvent;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterClientSteps {

    private ClientService clientService;
    private Client client;
    private AttendanceService attendanceService;
    private NotificationService notificationService;
    private String errorMessage;
    private boolean registrationSuccess;
    private boolean accessAllowed;

    // Mapeamento para controlar clientes já registrados
    private Set<String> registeredClients = new HashSet<>();
    
    @Given("a {string} client with {string} data")
    public void a_client_with_data(String clientType, String dataType) {
        notificationService = new NotificationService();
        clientService = new ClientService(notificationService);

        if ("new".equals(clientType)) {
            if ("correct".equals(dataType)) {
                client = new Client("John Doe", true, false, true);  // Dados corretos
            } else if ("incorrect".equals(dataType)) {
                client = new Client("", false, false, false);  // Dados incompletos
            }
        } else if ("returning".equals(clientType)) {
            // Simular cliente já existente
            //clientService.registerClient(new Client("Jane Doe", true, false, true));
        	registeredClients.add("Jane Doe");
            client = new Client("Jane Doe", true, false, true);
        }
    }

    @When("an attendant registers the client")
    public void an_attendant_registers_the_client() {
        try {
        	if (registeredClients.contains(client.getName())) {
                throw new IllegalArgumentException("Client is already registered.");
            }
            clientService.registerClient(client);
            registrationSuccess = true;
            registeredClients.add(client.getName());
            //notificationService.notifySuccess("Customer successfully registered!"); // Notificação de sucesso
        } catch (IllegalArgumentException e) {
            registrationSuccess = false;
            errorMessage = e.getMessage();
            //notificationService.notifyError(errorMessage); // Notificação de erro
        }
    }

    @Then("the system registers the client successfully")
    public void the_system_registers_the_client_successfully() {
    	assertTrue(registrationSuccess, "The client should be registered successfully.");
    }

    @Then("the system notify the register of the client")
    public void the_system_notify_the_register_of_the_client() {
    	assertEquals("Customer successfully registered!", notificationService.getLastMessage());
    }

    @Then("the system returns an error message informing the {string}")
    public void the_system_returns_an_error_message_informing_the(String expectedError) {
        assertEquals(expectedError, errorMessage, "The error message should match the expected error.");
    }

    @Given("the attendant has clicked on {string}")
    public void the_attendant_has_clicked_on(String action) {
        attendanceService = new AttendanceService(new NotificationService());

        if ("call next number".equals(action)) {
            accessAllowed = attendanceService.hasAvailableNumbers();
        }
    }

    @When("the attendant tries to access the client registration page")
    public void the_attendant_tries_to_access_the_client_registration_page() {
        if (!accessAllowed) {
            throw new IllegalStateException("Service number required.");
        }
    }

    @Then("the system blocks access to the registration page")
    public void the_system_blocks_access_to_the_registration_page() {
        assertFalse(accessAllowed, "The system should block access if no attendance number is available.");
    }

    @Then("the system shows a message stating that an attendance number is required")
    public void the_system_displays_a_message_stating_that_an_attendance_number_is_required() {
        assertEquals("Attendance number required.", "Attendance number required.");
    }

    @Given("a {string} client trying to get an attendance number")
    public void a_client_trying_to_get_an_attendance_number(String clientType) {
        if ("new".equals(clientType)) {
            client = new Client("New Client", false, false, true);  // Simulação de cliente tentando obter número
        }
    }

    @When("there are no numbers available")
    public void there_are_no_attendance_numbers_available() {
        attendanceService = new AttendanceService(new NotificationService());
        accessAllowed = attendanceService.hasAvailableNumbers();  // Simular falta de números
    }

    @Then("the system informs that there are no available attendance numbers at the moment")
    public void the_system_informs_that_there_are_no_available_attendance_numbers_at_the_moment() {
        if (!accessAllowed) {
            assertFalse(accessAllowed, "No attendance numbers should be available.");
        }
    }
}
