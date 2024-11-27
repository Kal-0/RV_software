package domain.entities.testresult;

import java.util.List;

public interface TestResultRepository {

	public void save(TestResult testResult);
	
	public void delete(TestResultId id);
	
	public TestResult get(TestResultId id);
	
	public void update(TestResult testResult);
	
	public List<TestResult> getAll();
	
}
