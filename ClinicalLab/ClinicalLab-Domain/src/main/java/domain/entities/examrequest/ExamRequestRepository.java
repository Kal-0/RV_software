package domain.entities.examrequest;



public interface ExamRequestRepository {

	public void save(ExamRequest examRequest);
	
	public void delete(ExamRequestId id);
	
	public ExamRequest get(ExamRequestId id);
	
	public void update(ExamRequest examRequest);
	
}
