package domain.entities.client;

import java.util.List;

import domain.entities.person.Cpf;

public interface ClientRepository {
	
	public void save(Client client);
	
	public void delete(ClientId clientId);
	
	public void delete(Cpf cpf);
	
	public Client get(ClientId clientId);
	
	public Client get(Cpf clientCPF);
	
	public Client get(String name);
	
	public List<Client> getAll();
	
	public void update(Client client);
	
	
}
