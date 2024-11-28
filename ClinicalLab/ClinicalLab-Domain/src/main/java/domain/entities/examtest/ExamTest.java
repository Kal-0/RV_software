package domain.entities.examtest;

import domain.entities.exam.ExamId;
import domain.entities.testresult.TestResultId;

public class ExamTest {
	
	private ExamTestId id;
    private ExamId examId;
    private TestResultId testResultId;
    private String status; 
    
	public ExamTest(ExamTestId id, ExamId examId, TestResultId testResultId, String status) {
		super();
		this.id = id;
		this.examId = examId;
		this.testResultId = testResultId;
		this.status = status;
	}
	
	
	
	public void setId(ExamTestId id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    

}
