package infrastructure.persistence.jpa;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Clients")
public class ClientJPA extends PersonJPA {

	private int clientId;
	
	public void setClientId(int id) {
		clientId = id;
		
		this.setId(clientId);
	}
	
	public int getClientId() {
		return this.getId();
	}


    
}
