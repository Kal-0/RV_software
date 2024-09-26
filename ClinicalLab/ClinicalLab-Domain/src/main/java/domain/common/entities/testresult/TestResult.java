package domain.common.entities.testresult;

import java.util.Date;

public class TestResult {
	private TestResultId id;
    private Date resultDate;
    private String resultContent;
    
	public TestResult(TestResultId id, Date resultDate, String resultContent) {
		super();
		this.id = id;
		this.resultDate = resultDate;
		this.resultContent = resultContent;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
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
