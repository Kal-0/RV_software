package domain.common.entities.clientservice;

public interface ClientServiceRepository {

	public void save(ClientService clientService);
	
	public void delete(ClientServiceId id);
	
	public ClientService get(ClientServiceId id);
	
	public void update(ClientService clientService);
	
}
