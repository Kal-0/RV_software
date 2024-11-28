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


public class TotalPriceService {
	
	private final ExamRequestRepository examRequestRepository;
    private final ExamTestRepository examTestRepository;
    private final ExamRepository examRepository;

    public TotalPriceService(ExamRequestRepository examRequestRepository, ExamTestRepository examTestRepository, ExamRepository examRepository) {
        this.examRequestRepository = examRequestRepository;
    	this.examTestRepository = examTestRepository;
        this.examRepository = examRepository;
    }

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
        return totalPrice;
    }
}
