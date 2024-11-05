package infrastructure.persistence.jpa.repositories;


import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.client.ClientRepository;
import domain.entities.person.Cpf;
import infrastructure.persistence.jpa.ClientJPA;
import infrastructure.persistence.jpa.JPAMapper;
import infrastructure.persistence.jpa.repository.ClientJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientJPARepository clientJPARepository;

    @Autowired
    private JPAMapper jpaMapper;

    @Override
    public void save(Client client) {
        ClientJPA clientJPA = jpaMapper.map(client, ClientJPA.class);
        clientJPARepository.save(clientJPA);
    }

    @Override
    public void delete(ClientId clientId) {
        clientJPARepository.deleteById((long) clientId.getId());
    }

    @Override
    public void delete(Cpf cpf) {
        ClientJPA clientJPA = clientJPARepository.findByCpf(cpf.getCpf());
        if (clientJPA != null) {
            clientJPARepository.delete(clientJPA);
        }
    }

    @Override
    public Client get(ClientId clientId) {
        ClientJPA clientJPA = clientJPARepository.findById((long) clientId.getId()).orElse(null);
        return jpaMapper.map(clientJPA, Client.class);
    }

    @Override
    public Client get(Cpf clientCPF) {
        ClientJPA clientJPA = clientJPARepository.findByCpf(clientCPF.getCpf());
        return jpaMapper.map(clientJPA, Client.class);
    }

    @Override
    public Client get(String name) {
        ClientJPA clientJPA = clientJPARepository.findByName(name);
        return jpaMapper.map(clientJPA, Client.class);
    }

    @Override
    public void update(Client client) {
        ClientJPA clientJPA = jpaMapper.map(client, ClientJPA.class);
        clientJPARepository.save(clientJPA);
    }
}

