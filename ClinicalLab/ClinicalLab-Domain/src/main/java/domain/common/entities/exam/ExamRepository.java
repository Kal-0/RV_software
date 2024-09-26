package domain.common.entities.exam;

public interface ExamRepository {

	public void save(Exam exam);
	
	public void delete(ExamId id);
	
	public Exam get(ExamId id);
	
	public void update(Exam exam);
	
}
