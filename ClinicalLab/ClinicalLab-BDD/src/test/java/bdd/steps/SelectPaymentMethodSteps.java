package bdd.steps;

import domain.entities.client.ClientId;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examtest.ExamTestId;
import domain.services.ExamRequestService;
import domain.services.TotalPriceService;
import infrastructure.persistence.memory.MemoryRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SelectPaymentMethodSteps {

    private MemoryRepository memoryRepository = new MemoryRepository();
    private ExamRequestService examRequestService = new ExamRequestService(memoryRepository, new TotalPriceService(memoryRepository, memoryRepository));
    private ExamRequest examRequest;
    private String paymentMethod;
    private RuntimeException exception;

    @Given("the system has not calculated the total price")
    public void the_system_has_not_calculated_the_total_price() {
        examRequest = new ExamRequest(new ExamRequestId(1), null, Arrays.asList(new ExamTestId(1)), null, null, null, "new");
        memoryRepository.save(examRequest);
    }

    @When("the attendant tries to select a {string}")
    public void the_attendant_tries_to_select_a_payment_method(String paymentMethod) {
        try {
            System.out.println("Select a payment method: Health Insurance = 1, Private Payment = 0");

            this.paymentMethod = paymentMethod;
            examRequestService.selectPaymentMethod(examRequest.getExamRequestId(), this.paymentMethod);
        } catch (RuntimeException e) {
            exception = e;
        }
    }

    @Then("the system will show an error message stating that the total price must be calculated before choosing a payment method")
    public void the_system_will_show_an_error_message_stating_that_the_total_price_must_be_calculated_before_choosing_a_payment_method() {
        assertNotNull(exception, "A RuntimeException should be thrown");
        assertEquals("Total price must be calculated before selecting a payment method", exception.getMessage());
    }

    @Given("a client with a not empty list of ExamTest")
    public void client_with_a_not_empty_list_of_exam_test() {
        List<ExamTestId> examTestList = Arrays.asList(
            new ExamTestId(1), 
            new ExamTestId(2)
        );
       
        ClientId clientId = new ClientId(1);
        ExamRequestId examRequestId = new ExamRequestId(1);
        
        examRequest = new ExamRequest(examRequestId, clientId, examTestList, LocalDate.now(), 100.0, null, "new");
        memoryRepository.save(examRequest);
    }

    @Given("the system has calculated the total price")
    public void the_system_has_calculated_the_total_price() {
        examRequest.setTotalPrice(100.0);
    }

    @When("the attendant completes the payment and the payment is confirmed")
    public void the_attendant_completes_the_payment_and_the_payment_is_confirmed() {
        examRequestService.completePayment(examRequest.getExamRequestId());
    }

    @Then("the system assigns the exam request status {string}")
    public void the_system_assigns_the_exam_request_status(String status) {
        assertEquals(status, examRequest.getStatus(), "Status should be updated to " + status);
        System.out.println("Exam request status: " + status);
    }

    @When("the attendant selects {string}")
    public void the_attendant_selects(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        examRequestService.selectPaymentMethod(examRequest.getExamRequestId(), this.paymentMethod);
    }
}
