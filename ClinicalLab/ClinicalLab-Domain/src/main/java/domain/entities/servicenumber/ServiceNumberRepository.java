package domain.entities.servicenumber;

import java.util.List;

public interface ServiceNumberRepository {

	public void save(ServiceNumber serviceNumber);
	
	public void delete(ServiceNumberId id);
	
	public ServiceNumber get(ServiceNumberId id);
	
	public void update(ServiceNumber serviceNumber);
	
	public List<ServiceNumber> getServiceNumberAll();
}
