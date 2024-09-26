package domain.common.entities.examtest;

import domain.common.entities.exam.ExamId;
import domain.common.entities.testresult.TestResultId;

public class ExamTest {
	
	private ExamTestId id;
    private ExamId examId;
    private TestResultId testResultId;
    
	public ExamTest(ExamTestId id, ExamId examId, TestResultId testResultId) {
		super();
		this.id = id;
		this.examId = examId;
		this.testResultId = testResultId;
	}

	public ExamTestId getId() {
		return id;
	}

	public ExamId getExamId() {
		return examId;
	}

	public TestResultId getTestResultId() {
		return testResultId;
	}
    
    

}
