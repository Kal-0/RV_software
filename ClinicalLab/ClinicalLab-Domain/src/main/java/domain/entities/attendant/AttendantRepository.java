package domain.entities.attendant;

public interface AttendantRepository {
	
	public void save(Attendant attendant);
	
	public void delete(AttendantId attendantId);
	
	public Attendant get(AttendantId attendantId);
	
	public void update(Attendant attendant);
	
}
