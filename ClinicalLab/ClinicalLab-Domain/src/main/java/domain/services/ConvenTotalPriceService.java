package domain.services;

import java.util.List;

import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examrequest.ExamRequestRepository;
import domain.entities.examtest.ExamTestId;
import domain.entities.examtest.ExamTestRepository;
import domain.services.interfaces.PayamentStrategy;

public class ConvenTotalPriceService implements PayamentStrategy {
	private final ExamRequestRepository examRequestRepository;
    private final ExamTestRepository examTestRepository;
    private final ExamRepository examRepository;

    public ConvenTotalPriceService(ExamRequestRepository examRequestRepository, ExamTestRepository examTestRepository, ExamRepository examRepository) {
        this.examRequestRepository = examRequestRepository;
    	this.examTestRepository = examTestRepository;
        this.examRepository = examRepository;
    }
    
    @Override
    public Double calculateTotalPrice(ExamRequestId examRequestId) {
    	ExamRequest examRequest = examRequestRepository.get(examRequestId);
        List<ExamTestId> examTestList = examRequest.getExamTestList();
        double totalPrice = 0.0;

        for (ExamTestId examTestId : examTestList) {
            ExamId examId = examTestRepository.get(examTestId).getExamId();
            Exam exam = examRepository.get(examId);
            
            if (exam != null) {
                totalPrice += exam.getPrice();
            }
        }
        
        examRequest.setTotalPrice(totalPrice);
        return totalPrice * 0.8; // desconto do convenio medico
    }
}
