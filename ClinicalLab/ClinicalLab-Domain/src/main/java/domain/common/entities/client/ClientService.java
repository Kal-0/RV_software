package domain.common.entities.client;

import java.util.NoSuchElementException;

import domain.common.entities.person.Cpf;

public class ClientService {
	
    private ClientRepository clientRepository;

    public ClientService(ClientRepository repository) {
        this.clientRepository = repository;
    }

    public Client getClientByCpf(Cpf cpf) {
        Client client = clientRepository.get(cpf);
        if (client != null) {
            return client;
        } else {
            throw new NoSuchElementException("No client found with the given CPF.");
        }
    }
}