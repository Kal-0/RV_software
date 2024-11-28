package infrastructure.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.client.ClientRepository;
import domain.entities.person.Cpf;
import infrastructure.persistence.jpa.repository.ClientJPARepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clients")
@PrimaryKeyJoinColumn(name = "id") // Define que 'id' é a chave estrangeira que referencia 'People'
public class ClientJPA extends PersonJPA {
	
	@Column(unique = true)
	private Integer clientId;
	
	
	
	
	@PostPersist
	private void generateClientId() {
	    if (this.clientId == null && this.getId() != null) {
	        this.clientId = this.getId(); // Use a lógica que faz sentido no seu caso
	    
	    }
	}
	
	
	public void setClientId(Integer id) {
		clientId = id;
	}
	
	public Integer getClientId() {
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
    public Client save(Client client) {
        ClientJPA clientJPA = mapper.map(client, ClientJPA.class);
        clientJPA = clientJPARepository.save(clientJPA);
        clientJPA = clientJPARepository.save(clientJPA);
        client.setClientId(new ClientId(clientJPA.getClientId()));
        return client;
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
	public List<Client> getClientAll() {
	    // Busca todas as entidades ClientJPA do banco de dados
	    List<ClientJPA> clientsJPA = clientJPARepository.findAll();

	    // Mapeia cada ClientJPA para Client (domÃ­nio) usando o mapper
	    return clientsJPA.stream()
	                     .map(clientJPA -> mapper.map(clientJPA, Client.class))
	                     .collect(Collectors.toList());
	}
	
    @Override
    public void update(Client client) {
        ClientJPA clientJPA = mapper.map(client, ClientJPA.class);
        clientJPARepository.save(clientJPA);
    }

	

	

	
}
