package infrastructure.domainevent;

import domain.entities.examtest.ExamTest;

public class ExamTestSavedEvent {
    private final ExamTest examTest;

    public ExamTestSavedEvent(ExamTest examTest) {
        this.examTest = examTest;
    }
    
    public ExamTest getExamTest() {
        return examTest;
    }

    @Override
    public String toString() {
        return "ExamTestSavedEvent{" +
                "examTestId=" + examTest.getId() +
                ", status=" + examTest.getStatus() +
                '}';
    }
}

