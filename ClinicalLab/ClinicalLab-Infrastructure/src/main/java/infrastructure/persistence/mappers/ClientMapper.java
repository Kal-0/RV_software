package infrastructure.persistence.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entities.client.Client;
import infrastructure.persistence.jpa.ClientJPA;
import infrastructure.persistence.jpa.JPAMapper;

@Component
public class ClientMapper {

    @Autowired
    private JPAMapper jpaMapper;

    public Client map(ClientJPA clientJPA) {
        return jpaMapper.map(clientJPA, Client.class);
    }

    public ClientJPA map(Client client) {
        return jpaMapper.map(client, ClientJPA.class);
    }
}
