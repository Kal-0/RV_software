package domain.entities.serviceoverview;

public interface ServiceStatusRepository {
	
    void save(ServiceStatus serviceStatus);
    
    void delete(ServiceStatusId serviceStatusId);
    
    ServiceStatus get(ServiceStatusId serviceStatusId);
    
    void update(ServiceStatus serviceStatus);
    
}

