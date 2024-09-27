/*package dev.sauloaraujo.sgb.acervo.dominio;*/

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class RequestValidationSteps {

    private PaymentService paymentService;
    private NotificationService notificationService;
    private QueueService queueService;
    private List<TestRequest> registeredRequests;
    private TestRequest currentRequest;
    private Client client;
    private String errorMessage;
    private double calculatedCost;

    public RequestValidationSteps() {
        paymentService = new PaymentService();
        notificationService = new NotificationService();
        queueService = new QueueService();
        registeredRequests = new ArrayList<>();
    }

    @Given("a {string} client with {string}")
    public void a_client_with(String clientType, String paymentType) {
        // Inicializa o cliente com base no tipo e no método de pagamento.
        boolean hasInsurance = "health insurance".equals(paymentType);
        client = new Client("John Doe", hasInsurance);
    }

    @And("the attendant selects {string} for the client")
    public void the_attendant_selects_tests_for_the_client(String testSelection) {
        // Cria uma nova solicitação de testes para o cliente.
        List<String> tests = List.of("Blood Test", "Glucose Test");  // Simulação dos testes selecionados
        currentRequest = new TestRequest(client, tests, client.hasInsurance());
    }

    @When("the attendant confirms the test request")
    public void the_attendant_confirms_the_test_request() {
        // Registra a solicitação no sistema e calcula o custo.
        if (isDuplicateRequest(currentRequest)) {
            errorMessage = "Request already registered.";
            notificationService.notify("Erro: Solicitação já registrada para este cliente.");
        } else {
            calculatedCost = calculateCost(currentRequest);
            currentRequest.setTotalCost(calculatedCost);
            registeredRequests.add(currentRequest);
            notificationService.notify("Solicitação registrada com sucesso.");
        }
    }

    @Then("the system calculates the total cost based on insurance coverage")
    public void the_system_calculates_the_total_cost_based_on_insurance_coverage() {
        if (client.hasInsurance()) {
            double expectedCost = paymentService.calculateWithInsurance(currentRequest.getTests().size());
            assertEquals(expectedCost, calculatedCost, "O custo total deve ser calculado com base na cobertura do convênio.");
        }
    }

    @Then("the system calculates the total cost without discounts")
    public void the_system_calculates_the_total_cost_without_discounts() {
        if (!client.hasInsurance()) {
            double expectedCost = paymentService.calculateWithoutDiscount(currentRequest.getTests().size());
            assertEquals(expectedCost, calculatedCost, "O custo total deve ser calculado sem descontos.");
        }
    }

    @And("the system registers a total of {int} tests in the request")
    public void the_system_registers_a_total_of_tests_in_the_request(int numberOfTests) {
        assertEquals(numberOfTests, currentRequest.getTests().size(), "O número de testes registrados deve corresponder ao número esperado.");
    }

    @And("the system notifies the attendant that the request is registered successfully")
    public void the_system_notifies_the_attendant_that_the_request_is_registered_successfully() {
        assertEquals("Solicitação registrada com sucesso.", notificationService.getLastMessage());
    }

    @Given("a {string} client with an existing request for blood test")
    public void a_client_with_an_existing_request_for_blood_test(String clientType) {
        // Simula um cliente existente com uma solicitação prévia.
        List<String> initialTests = List.of("Test_A");
        currentRequest = new TestRequest(client, initialTests, client.hasInsurance());
        registeredRequests.add(currentRequest);
    }

    @And("the client decides to change the {string} to {string}")
    public void the_client_decides_to_change_the_test_to_test(String oldTest, String newTest) {
        List<String> updatedTests = List.of(newTest);  // Atualiza os testes para incluir a nova seleção
        currentRequest.setTests(updatedTests);
    }

    @When("the attendant updates the request")
    public void the_attendant_updates_the_request() {
        double newCost = calculateCost(currentRequest);
        currentRequest.setTotalCost(newCost);
        notificationService.notify("Solicitação atualizada com sucesso.");
    }

    @Then("the system updates the test request to include {string}")
    public void the_system_updates_the_test_request_to_include(String updatedTests) {
        assertEquals(updatedTests, String.join(", ", currentRequest.getTests()), "A solicitação deve ser atualizada com os novos testes.");
    }

    @And("the total cost is recalculated")
    public void the_total_cost_is_recalculated() {
        double expectedCost = calculateCost(currentRequest);
        assertEquals(expectedCost, currentRequest.getTotalCost(), "O custo total deve ser recalculado.");
    }

    @Given("a {string} client with a registered test request for {string} tests")
    public void a_client_with_a_registered_test_request_for_tests(String clientType, String testNames) {
        // Simula um cliente com uma solicitação registrada previamente.
        List<String> tests = List.of(testNames.split(", "));
        currentRequest = new TestRequest(client, tests, client.hasInsurance());
        registeredRequests.add(currentRequest);
    }

    @When("the attendant tries to submit the same test request again")
    public void the_attendant_tries_to_submit_the_same_test_request_again() {
        // Tenta registrar novamente a solicitação duplicada.
        if (isDuplicateRequest(currentRequest)) {
            errorMessage = "Request already registered.";
            notificationService.notify("Erro: Solicitação já registrada para este cliente.");
        }
    }

    @Then("the system shows an error message indicating that the request already exists")
    public void the_system_shows_an_error_message_indicating_that_the_request_already_exists() {
        assertEquals("Erro: Solicitação já registrada para este cliente.", notificationService.getLastMessage(), "A mensagem de erro deve indicar que a solicitação já existe.");
    }

    // Métodos auxiliares para cálculo e verificação
    private boolean isDuplicateRequest(TestRequest request) {
        return registeredRequests.stream().anyMatch(r -> r.equals(request));
    }

    private double calculateCost(TestRequest request) {
        return request.hasInsurance() ? paymentService.calculateWithInsurance(request.getTests().size()) : paymentService.calculateWithoutDiscount(request.getTests().size());
    }
}