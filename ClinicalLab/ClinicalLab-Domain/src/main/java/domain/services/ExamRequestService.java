 package domain.services;

import java.time.LocalDate;
import java.util.List;

import domain.entities.client.ClientId;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examrequest.ExamRequestRepository;
import domain.entities.examtest.ExamTestId;


public class ExamRequestService {

    private final ExamRequestRepository examRequestRepository;
    private final TotalPriceService totalPriceService;

    public ExamRequestService(ExamRequestRepository examRequestRepository, TotalPriceService totalPriceService) {
        this.examRequestRepository = examRequestRepository;
        this.totalPriceService = totalPriceService;
    }


public ExamRequest saveExamRequest(ExamRequest examRequest) {

    if (examRequestRepository.get(examRequest.getExamRequestId()) != null) {
        throw new IllegalArgumentException("The exam request already exists");
    }

    if (examRequest.getExamTestList() == null || examRequest.getExamTestList().isEmpty()) {
        throw new IllegalArgumentException("Exam Test List must not be empty");
    }

    examRequestRepository.save(examRequest);
    return examRequestRepository.get(examRequest.getExamRequestId());
}

    public void processExamRequest(ExamRequestId examRequestId) {
   
        ExamRequest examRequest = examRequestRepository.get(examRequestId);
        
        if (examRequest == null) {
            throw new IllegalArgumentException("ExamRequest not found");
        }

        totalPriceService.calculateTotalPrice(examRequest);
        
        examRequestRepository.update(examRequest);
    }
    
    public void selectPaymentMethod(ExamRequestId examRequestId, String paymentMethod) {
        ExamRequest examRequest = examRequestRepository.get(examRequestId);

        if (examRequest.getTotalPrice() == null || examRequest.getTotalPrice() == 0) {
            throw new RuntimeException("Total price must be calculated before selecting a payment method");
        }

        examRequest.setPaymentMethod(paymentMethod);
        examRequestRepository.update(examRequest);
    }

    public void completePayment(ExamRequestId examRequestId) {
        ExamRequest examRequest = examRequestRepository.get(examRequestId);

        if (examRequest.getTotalPrice() == null || examRequest.getTotalPrice() == 0) {
            throw new RuntimeException("Payment cannot be completed without total price calculation");
        }

        examRequest.setStatus("Waiting for collection");
        examRequestRepository.update(examRequest);
    }
}
