package domain.service;

import java.time.LocalDate;
import java.util.List;

import domain.common.entities.client.ClientId;
import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examrequest.ExamRequestId;
import domain.common.entities.examrequest.ExamRequestRepository;
import domain.common.entities.examtest.ExamTestId;


public class ExamRequestService {

    private final ExamRequestRepository examRequestRepository;
    private final TotalPriceService totalPriceService;

    public ExamRequestService(ExamRequestRepository examRequestRepository, TotalPriceService totalPriceService) {
        this.examRequestRepository = examRequestRepository;
        this.totalPriceService = totalPriceService;
    }

  
    public ExamRequest createExamRequest(ClientId clientId, List<ExamTestId> examTestList, String paymentMethod) {
        
        if (examTestList == null || examTestList.isEmpty()) {
            throw new IllegalArgumentException("A lista de testes de exame não pode estar vazia");
        }
        
        ExamRequestId examRequestId = new ExamRequestId(); 
        
        LocalDate requestDate = LocalDate.now();
        
        String status = "Novo";
        
        Double totalPrice = 0.0;
        
        ExamRequest newExamRequest = new ExamRequest(examRequestId, clientId, examTestList, requestDate, totalPrice, paymentMethod, status);
        
        examRequestRepository.save(newExamRequest);
        
        return newExamRequest;
    }
    

    public void processExamRequest(ExamRequestId examRequestId) {
   
        ExamRequest examRequest = examRequestRepository.get(examRequestId);
        
        if (examRequest == null) {
            throw new IllegalArgumentException("ExamRequest não encontrado");
        }

        totalPriceService.calculateTotalPrice(examRequest);
        
        examRequestRepository.update(examRequest);
    }
}
