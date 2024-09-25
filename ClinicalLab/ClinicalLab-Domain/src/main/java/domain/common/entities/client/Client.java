package domain.common.entities.client;

import domain.common.entities.person.Person;

public class Client extends Person {
    private ClientId clientId;
    
    
    
	public ClientId getClientId() {
		return this.clientId;
	}

	public void setClientId(ClientId id) {
		this.clientId = id;
	}

    
}
