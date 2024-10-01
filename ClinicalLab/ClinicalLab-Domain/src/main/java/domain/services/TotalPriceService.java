package domain.services;

import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examtest.ExamTestId;
import domain.common.entities.exam.Exam;
import domain.common.entities.exam.ExamId;
import domain.common.entities.exam.ExamRepository;
import domain.common.entities.examtest.ExamTestRepository;
import java.util.List;

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
