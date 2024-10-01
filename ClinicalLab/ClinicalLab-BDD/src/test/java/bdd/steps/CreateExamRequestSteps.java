package bdd.steps;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;

import domain.common.entities.client.ClientId;
import domain.common.entities.exam.Exam;
import domain.common.entities.exam.ExamId;
import domain.common.entities.exam.ExamRepository;
import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examrequest.ExamRequestId;
import domain.common.entities.examrequest.ExamRequestRepository;
import domain.common.entities.examtest.ExamTestId;
import domain.common.entities.examtest.ExamTestRepository;
import domain.service.ExamRequestService;
import domain.service.TotalPriceService;
import infrastructure.persistence.memory.MemoryRepository; // Supondo que você esteja usando a implementação em memória
import io.cucumber.java.en.*;

public class CreateExamRequestSteps {

    private ExamRequestRepository examRequestRepository;
    private ExamTestRepository examTestRepository;
    private ExamRepository examRepository; 
    private ExamRequestService examRequestService;
    private ExamRequest examRequest;
    private String errorMessage;

    @Given("a client without a registered exam requests")
    public void a_client_without_a_registered_exam_requests() {
        examRequestRepository = new MemoryRepository();
        examTestRepository = new MemoryRepository(); 
        examRepository = new MemoryRepository(); 
        
        TotalPriceService totalPriceService = new TotalPriceService(examTestRepository, examRepository);
        examRequestService = new ExamRequestService(examRequestRepository, totalPriceService);
    }

    @When("the attendant completes a new exam request")
    public void the_attendant_completes_a_new_exam_request() {
        ClientId clientId = new ClientId(1); 
        ExamRequestId requestId = new ExamRequestId(1);
        examRequest = new ExamRequest(requestId, clientId, Arrays.asList(new ExamTestId(1)), LocalDate.now(), 100.0, "Credit Card", "Pending");
        examRequestService.saveExamRequest(examRequest);
    }

    @When("change the exam request status to {string}")
    public void change_the_exam_request_status_to(String status) {
        examRequest.setStatus(status);
        examRequestRepository.update(examRequest);
    }

    @Then("the system saves the exam request")
    public void the_system_saves_the_exam_request() {
        ExamRequest savedRequest = examRequestRepository.get(examRequest.getExamRequestId());
        assertNotNull(savedRequest);
        assertEquals(examRequest.getExamRequestId().getId(), savedRequest.getExamRequestId().getId());
        assertEquals(examRequest.getStatus(), savedRequest.getStatus());
    }

    @Given("a client with an existing exam request")
    public void a_client_with_an_existing_exam_request() {
        ClientId clientId = new ClientId(1); 
        ExamRequestId requestId = new ExamRequestId(1);
        examRequest = new ExamRequest(requestId, clientId, Arrays.asList(new ExamTestId(1)), LocalDate.now(), 100.0, "Credit Card", "Pending");
        examRequestRepository = new MemoryRepository();
        examTestRepository = new MemoryRepository();
        examRepository = new MemoryRepository();
        TotalPriceService totalPriceService = new TotalPriceService(examTestRepository, examRepository);
        examRequestService = new ExamRequestService(examRequestRepository, totalPriceService);
        examRequestService.saveExamRequest(examRequest); 
    }

    @When("the attendant tries to register the same exam request again")
    public void the_attendant_tries_to_register_the_same_exam_request_again() {
        try {
            examRequestService.saveExamRequest(examRequest); 
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage(); 
        }
    }

    @Then("the system shows an error message indicating that the request already exists")
    public void the_system_shows_an_error_message_indicating_that_the_request_already_exists() {
        assertNotNull(errorMessage); 
        assertEquals("The exam request already exists", errorMessage); 
    }

    @Then("the system prevents duplicate submissions")
    public void the_system_prevents_duplicate_submissions() {
        ExamRequest existingRequest = examRequestRepository.get(examRequest.getExamRequestId());
        assertNotNull(existingRequest);
    }
}
