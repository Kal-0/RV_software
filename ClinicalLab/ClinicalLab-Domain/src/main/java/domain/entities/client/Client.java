package domain.entities.client;

import java.time.LocalDate;

import domain.entities.person.Cpf;
import domain.entities.person.Email;
import domain.entities.person.Person;
import domain.entities.person.PersonId;

public class Client extends Person {
    
	private ClientId clientId;
    
	public Client(PersonId id, Cpf cpf, Email contactEmail, String name, LocalDate birthDate, ClientId clientId) {
		super(id, cpf, contactEmail, name, birthDate);
		this.clientId = clientId;
	}

	public Client(int id, String cpf, String contactEmail, String name, String birthDate, int clientId) {
		super(id, cpf, contactEmail, name, birthDate);
		this.clientId = new ClientId(clientId);
	}

	public ClientId getClientId() {
		return this.clientId;
	}

	public void setClientId(ClientId id) {
		this.clientId = id;
	}
	
	@Override
	public String toString() {
	    return "Client{" +
	            "clientId=" + clientId +
	            ", id=" + getId() + 
	            ", cpf=" + getCpf() + 
	            ", email=" + getContactEmail() + 
	            ", name=" + getName() + 
	            ", birthDate=" + getBirthDate() +
	            '}';
	}

    
}
