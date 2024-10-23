package domain.entities.testresult;

import java.time.LocalDate;

public class TestResult {
	private TestResultId id;
    private LocalDate resultDate;
    private String resultContent;
    
	public TestResult(TestResultId id, LocalDate resultDate, String resultContent) {
		super();
		this.id = id;
		this.resultDate = resultDate;
		this.resultContent = resultContent;
	}

	public LocalDate getResultDate() {
		return resultDate;
	}

	public void setResultDate(LocalDate resultDate) {
		this.resultDate = resultDate;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}

	public TestResultId getId() {
		return id;
	}
    
	
    
}
