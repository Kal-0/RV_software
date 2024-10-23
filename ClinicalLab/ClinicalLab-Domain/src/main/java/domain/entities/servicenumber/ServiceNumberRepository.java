package domain.entities.servicenumber;

public interface ServiceNumberRepository {
	

	public void save(ServiceNumber serviceNumber);
	
	public void delete(ServiceNumberId id);
	
	public ServiceNumber get(ServiceNumberId id);
	
	public void update(ServiceNumber serviceNumber);
}
