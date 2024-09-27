package domain.common.entities.client;

import java.time.LocalDate;

import domain.common.entities.person.Cpf;
import domain.common.entities.person.Email;
import domain.common.entities.person.Person;
import domain.common.entities.person.PersonId;

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

    
}
