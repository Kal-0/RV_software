// package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;

//import org.jmolecules.event.types.DomainEvent;
import static org.junit.jupiter.api.Assertions.*;

public class ResultValidationSteps {

    private ClientService clientService;
    private ResultService resultService;
    private List<Client> clientList;
    private Client selectedClient;
    private String searchResultMessage;
    private TestRecord selectedTestRecord;
    private boolean emailSent;
    private boolean emailAvailable;

    @Given("the attendant is on the {string} page")
    public void the_attendant_is_on_the_page(String page) {
        // Simular que o atendente está na página de "Clientes"
        assertEquals("Clients", page, "O atendente deveria estar na página de Clientes.");
        clientService = new ClientService(new NotificationService());
    }

    @When("the attendant views the client list")
    public void the_attendant_views_the_client_list() {
        clientList = clientService.getClientsWithExams();
    }

    @Then("the system displays the list of all clients who have taken exams at the laboratory")
    public void the_system_displays_the_list_of_all_clients_who_have_taken_exams_at_the_laboratory() {
        assertNotNull(clientList, "A lista de clientes que realizaram exames não pode ser nula.");
        assertTrue(clientList.size() > 0, "A lista de clientes deve conter pelo menos um cliente.");
        System.out.println("Clientes que realizaram exames: " + clientList);
    }

    @Given("the attendant enters a {string} client name or CPF in the search bar")
    public void the_attendant_enters_a_client_name_or_CPF_in_the_search_bar(String clientType) {
        if ("non-existent".equals(clientType)) {
            searchResultMessage = clientService.searchClient("non-existent");
        } else if ("existent".equals(clientType)) {
            Client client = new Client("John Doe", "123.456.789-10");
            clientService.addClient(client);
            searchResultMessage = clientService.searchClient(client.getCpf());
        }
    }

    @When("the attendant clicks the search button")
    public void the_attendant_clicks_the_search_button() {
        // Ação já simulada na busca de cliente
    }

    @Then("the system displays a message stating that the client does not exist")
    public void the_system_displays_a_message_stating_that_the_client_does_not_exist() {
        assertEquals("Client does not exist.", searchResultMessage);
        System.out.println("Mensagem exibida: " + searchResultMessage);
    }

    @Given("the attendant searches for a {string} client")
    public void the_attendant_searches_for_a_client(String clientType) {
        if ("specific".equals(clientType)) {
            selectedClient = clientService.searchClientByNameOrCpf("John Doe");
        }
    }

    @When("the attendant clicks on the client's name")
    public void the_attendant_clicks_on_the_client_s_name() {
        assertNotNull(selectedClient, "O cliente deve existir para visualização.");
        selectedTestRecord = resultService.getTestRecordsForClient(selectedClient);
    }

    @Then("the system displays all test records of the client")
    public void the_system_displays_all_test_records_of_the_client() {
        assertNotNull(selectedTestRecord, "Os registros de testes não podem ser nulos.");
        System.out.println("Registros de testes do cliente: " + selectedTestRecord);
    }

    @Then("the system shows whether the results were received by email or got the printed result in PDF at the laboratory")
    public void the_system_shows_whether_the_results_were_received_by_email_or_got_the_printed_result_in_PDF_at_the_laboratory() {
        String deliveryMethod = selectedTestRecord.getDeliveryMethod();
        assertNotNull(deliveryMethod, "O método de entrega não pode ser nulo.");
        System.out.println("Método de entrega do resultado: " + deliveryMethod);
    }

    @When("the attendant clicks on the {string} button")
    public void the_attendant_clicks_on_the_button(String action) {
        if ("Export".equals(action)) {
            resultService.exportTestRecord(selectedTestRecord);
            assertTrue(resultService.isPdfExported(), "O registro do teste deve ser exportado como PDF.");
        } else if ("Resend by Email".equals(action)) {
            emailAvailable = selectedClient.hasEmail();
            if (emailAvailable) {
                emailSent = resultService.resendResultByEmail(selectedTestRecord);
            }
        }
    }

    @Then("the system allows the attendant to download the test record as a PDF")
    public void the_system_allows_the_attendant_to_download_the_test_record_as_a_PDF() {
        assertTrue(resultService.isPdfExported(), "O registro do teste deve ser exportado como PDF.");
        System.out.println("O registro do teste foi exportado como PDF.");
    }

    @And("confirms the action")
    public void confirms_the_action() {
        assertTrue(emailAvailable, "O cliente deve ter um e-mail disponível.");
        assertTrue(emailSent, "O e-mail deve ser enviado com sucesso.");
    }

    @Then("the system sends the test result to the client's registered email address")
    public void the_system_sends_the_test_result_to_the_client_s_registered_email_address() {
        assertTrue(emailSent, "O e-mail deve ser enviado.");
        System.out.println("O resultado do teste foi enviado por e-mail.");
    }

    @Then("the system displays a confirmation message that the email has been sent successfully")
    public void the_system_displays_a_confirmation_message_that_the_email_has_been_sent_successfully() {
        System.out.println("Mensagem exibida: E-mail enviado com sucesso.");
    }

    @And("the client does not have an email address on file")
    public void the_client_does_not_have_an_email_address_on_file() {
        emailAvailable = false;
    }

    @Then("the system displays a message stating that no email address is available for this client")
    public void the_system_displays_a_message_stating_that_no_email_address_is_available_for_this_client() {
        assertFalse(emailAvailable, "Nenhum e-mail deve estar disponível.");
        System.out.println("Mensagem exibida: Nenhum e-mail disponível para este cliente.");
    }

    @Then("the system does not send the test result")
    public void the_system_does_not_send_the_test_result() {
        assertFalse(emailSent, "O resultado do teste não deve ser enviado.");
    }

    @Given("the test results for a client are entered into the database")
    public void the_test_results_for_a_client_are_entered_into_the_database() {
        resultService.enterTestResultsForClient(selectedClient, selectedTestRecord);
    }

    @When("the system detects that the test results are complete")
    public void the_system_detects_that_the_test_results_are_complete() {
        assertTrue(resultService.areResultsComplete(selectedTestRecord), "Os resultados dos testes devem estar completos.");
    }

    @Then("the system automatically sends an email to the client")
    public void the_system_automatically_sends_an_email_to_the_client() {
        emailSent = resultService.sendResultByEmail(selectedTestRecord);
        assertTrue(emailSent, "O e-mail deve ser enviado automaticamente.");
    }

    @Then("the email contains the {string}")
    public void the_email_contains_the(String pageInfo) {
        assertEquals("Exam Request Number Page", pageInfo, "O e-mail deve conter o link da página do pedido de exame.");
    }

    @Then("in the {string} page the status from that {string} will change to {string}")
    public void in_the_page_the_status_from_that_will_change_to(String page, String requestNumber, String status) {
        assertEquals("Client Record", page);
        assertEquals("Exam Request Number", requestNumber);
        assertEquals("Sent Result", status);
        System.out.println("Status alterado para: " + status);
    }
}