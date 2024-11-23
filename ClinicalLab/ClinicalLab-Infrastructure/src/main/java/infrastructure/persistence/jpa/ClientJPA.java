package infrastructure.persistence.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.client.ClientRepository;
import domain.entities.person.Cpf;
import infrastructure.persistence.jpa.repository.ClientJPARepository;
import infrastructure.persistence.jpa.repository.ExamJPARepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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


@Repository
class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientJPARepository clientJPARepository;

    @Autowired
    private JPAMapper mapper; // Usar o ExamMapper

    @Override
    public void save(Client client) {
        ClientJPA clientJPA = mapper.map(client, ClientJPA.class);
        clientJPARepository.save(clientJPA);
    }
    
    @Override
    public void delete(ClientId id) {
        clientJPARepository.deleteByClientId(id.getId());
    }
    
    @Override
	public void delete(Cpf cpf) {
		// TODO Auto-generated method stub
    	clientJPARepository.deleteByCpf(cpf.getCpf());
	}

    @Override
    public Client get(ClientId id) {
        ClientJPA clientJPA = clientJPARepository.findByClientId(id.getId()).orElse(null);
        return mapper.map(clientJPA, Client.class);
    }
    
    @Override
	public Client get(Cpf cpf) {
		// TODO Auto-generated method stub
    	ClientJPA clientJPA = clientJPARepository.findByCpf(cpf.getCpf()).orElse(null);
        return mapper.map(clientJPA, Client.class);
	}

	@Override
	public Client get(String name) {
		// TODO Auto-generated method stub
		ClientJPA clientJPA = clientJPARepository.findByName(name).orElse(null);
        return mapper.map(clientJPA, Client.class);
	}

    @Override
    public void update(Client client) {
        ClientJPA clientJPA = mapper.map(client, ClientJPA.class);
        clientJPARepository.save(clientJPA);
    }

	

	
}
