package domain.common.entities.examtest;

public interface ExamTestRepository {

	public void save(ExamTest examTest);
	
	public void delete(ExamTestId id);
	
	public ExamTest get(ExamTestId id);
	
	public void update(ExamTest examTest);
	
}
