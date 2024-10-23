package domain.entities.testresult;

public interface TestResultRepository {

	public void save(TestResult testResult);
	
	public void delete(TestResultId id);
	
	public TestResult get(TestResultId id);
	
	public void update(TestResult testResult);
	
}
