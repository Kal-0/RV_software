package domain.services;

import java.util.List;

import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examtest.ExamTestId;
import domain.entities.examtest.ExamTestRepository;


public class TotalPriceService {

    private final ExamTestRepository examTestRepository;
    private final ExamRepository examRepository;

    public TotalPriceService(ExamTestRepository examTestRepository, ExamRepository examRepository) {
        this.examTestRepository = examTestRepository;
        this.examRepository = examRepository;
    }

    public void calculateTotalPrice(ExamRequest examRequest) {
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
    }
}
