package domain.common.entities.examrequest;

import java.time.LocalDate;
import java.util.List;

import domain.common.entities.client.ClientId;
import domain.common.entities.examtest.*;


public class ExamRequestService {

    private ExamRequestRepository examRequestRepository;

    public ExamRequestService(ExamRequestRepository examRequestRepository) {
        this.examRequestRepository = examRequestRepository;
    }

    // Método para criar uma nova solicitação de exame
    public ExamRequest createExamRequest(ClientId clientId, List<ExamTestId> examTestList, Double totalPrice, String paymentMethod) {
        
        // Gera um novo ID para a requisição de exame
        ExamRequestId examRequestId = new ExamRequestId(); // Pode ser um UUID ou um identificador gerado pelo sistema
        
        // Define a data de solicitação como a data atual
        LocalDate requestDate = LocalDate.now();
        
        // Status inicial da solicitação
        String status = "Novo";
        
        // Cria a nova solicitação de exame
        ExamRequest newExamRequest = new ExamRequest(examRequestId, clientId, examTestList, requestDate, totalPrice, paymentMethod, status);
        
        // Salva a nova requisição de exame no repositório
        examRequestRepository.save(newExamRequest);
        
        return newExamRequest;
    }
}
