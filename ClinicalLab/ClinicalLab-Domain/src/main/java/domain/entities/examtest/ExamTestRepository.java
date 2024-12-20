package domain.entities.examtest;

import java.util.List;

public interface ExamTestRepository {

	public ExamTest save(ExamTest examTest);
	
	public void delete(ExamTestId id);
	
	public ExamTest get(ExamTestId id);
	
	public void update(ExamTest examTest);
	
	public List<ExamTest> getExamTestAll();
	
}
