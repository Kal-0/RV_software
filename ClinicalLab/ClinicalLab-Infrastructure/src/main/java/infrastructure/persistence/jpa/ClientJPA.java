package infrastructure.persistence.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.client.ClientRepository;
import domain.entities.person.Cpf;
import infrastructure.persistence.jpa.repository.ClientJPARepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clients")
@PrimaryKeyJoinColumn(name = "id") // Define que 'id' é a chave estrangeira que referencia 'People'
public class ClientJPA extends PersonJPA {
	@Column(unique = true)
	private Integer clientId;
	
	public void setClientId(Integer id) {
		clientId = id;
		
		this.setId(clientId);
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
	public List<Client> getClientAll() {
	    // Busca todas as entidades ClientJPA do banco de dados
	    List<ClientJPA> clientsJPA = clientJPARepository.findAll();

	    // Mapeia cada ClientJPA para Client (domínio) usando o mapper
	    return clientsJPA.stream()
	                     .map(clientJPA -> mapper.map(clientJPA, Client.class))
	                     .toList();
	}
	
    @Override
    public void update(Client client) {
        ClientJPA clientJPA = mapper.map(client, ClientJPA.class);
        clientJPARepository.save(clientJPA);
    }

	

	

	
}
