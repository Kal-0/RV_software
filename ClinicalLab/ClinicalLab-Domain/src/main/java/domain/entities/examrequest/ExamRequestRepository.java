package domain.entities.examrequest;

import java.util.List;

public interface ExamRequestRepository {

	public void save(ExamRequest examRequest);
	
	public void delete(ExamRequestId id);
	
	public ExamRequest get(ExamRequestId id);
	
	public List<ExamRequest> getAll();
	
	public void update(ExamRequest examRequest);
	
}
