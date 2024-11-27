package domain.entities.exam;

import java.util.List;

public interface ExamRepository {

	public void save(Exam exam);
	
	public void delete(ExamId id);
	
	public Exam get(ExamId id);
	
	public void update(Exam exam);
	
	public List<Exam> getExamAll();
	
}
