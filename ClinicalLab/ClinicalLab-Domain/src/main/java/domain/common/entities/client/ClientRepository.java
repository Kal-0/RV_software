package domain.common.entities.client;

public interface ClientRepository {
	
	public void save(Client client);
	
	public void delete(ClientId clientId);
	
	public Client get(ClientId clientId);
	
	public void update(Client client);
	
}
