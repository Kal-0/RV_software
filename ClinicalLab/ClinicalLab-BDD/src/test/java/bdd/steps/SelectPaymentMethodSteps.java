package bdd.steps;

import domain.common.entities.client.ClientId;
import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examrequest.ExamRequestId;
import domain.common.entities.examtest.ExamTestId;
import domain.service.ExamRequestService;
import domain.service.TotalPriceService;
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
        examRequest = new ExamRequest(new ExamRequestId(), null, Arrays.asList(new ExamTestId(1)), null, null, null, "new");
        memoryRepository.save(examRequest);
    }

    @When("the attendant tries to select a {string}")
    public void the_attendant_tries_to_select_a_payment_method(String paymentMethod) {
        try {
            System.out.println("Select a payment method: Health Insurance = 1, Private Payment = 0");

            if ("Health Insurance".equals(paymentMethod)) {
                this.paymentMethod = "Health Insurance";
            } else if ("Private Payment".equals(paymentMethod)) {
                this.paymentMethod = "Private Payment";
            } else {
                throw new RuntimeException("Invalid payment method selected");
            }

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

    @Given("the system has calculated the total price")
    public void the_system_has_calculated_the_total_price() {
        List<ExamTestId> examTestList = Arrays.asList(new ExamTestId(1), new ExamTestId(2));
        examRequest = new ExamRequest(new ExamRequestId(), null, examTestList, null, 100.0, null, "new");
        memoryRepository.save(examRequest);
    }

    @When("the attendant selects {string}")
    public void the_attendant_selects(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        examRequestService.selectPaymentMethod(examRequest.getExamRequestId(), this.paymentMethod);
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

    @Then("the system will show a successfully message")
    public void the_system_will_show_a_successfully_message() {
        assertEquals(paymentMethod, examRequest.getPaymentMethod(), "Payment method should be set");
        System.out.println("Payment method confirmed: " + paymentMethod);
    }

    @Given("the ExamRequest has a not empty list of ExamTest and a calculated totalprice")
    public void the_exam_request_has_a_not_empty_list_of_exam_test_and_a_calculated_totalprice() {
        List<ExamTestId> examTestList = Arrays.asList(new ExamTestId(1), new ExamTestId(2));
        examRequest = new ExamRequest(new ExamRequestId(), null, examTestList, null, 100.0, null, "new");
        memoryRepository.save(examRequest);
    }

    @Given("a client with a not empty list of ExamTest")
    public void client_with_a_not_empty_list_of_exam_test() {
        List<ExamTestId> examTestList = Arrays.asList(
            new ExamTestId(1), 
            new ExamTestId(2)
        );
       
        ClientId clientId = new ClientId(1);
        ExamRequestId examRequestId = new ExamRequestId();
        
        examRequest = new ExamRequest(examRequestId, clientId, examTestList, LocalDate.now(), 100.0, null, "new");
        memoryRepository.save(examRequest);
    }
}
