package domain.service;

import java.util.NoSuchElementException;

import domain.common.entities.client.Client;
import domain.common.entities.client.ClientId;
import domain.common.entities.client.ClientRepository;
import domain.common.entities.person.Cpf;

public class ClientService {
	
    private ClientRepository clientRepository;

    public ClientService(ClientRepository repository) {
        this.clientRepository = repository;
    }
    	
    
    public Client save(Client client) {
        if (client != null) {
        	clientRepository.save(client);
            return clientRepository.get(client.getClientId());
        } else {
            throw new NoSuchElementException("Client must not be null");
        }	
    }
    
    public Client getClientByCpf(Cpf cpf) {
        Client client = clientRepository.get(cpf);
        if (client != null) {
            return client;
        } else {
            throw new NoSuchElementException("No client found with the given CPF.");
        }	
    }
    
    public Client getClientById(ClientId id) {
        Client client = clientRepository.get(id);
        if (client != null) {
            return client;
        } else {
            throw new NoSuchElementException("No client found with the given ID.");
        }
    }
    
    public Client getClientByName(String name) {
        Client client = clientRepository.get(name);
        if (client != null) {
            return client;
        } else {
            throw new NoSuchElementException("No client found with the given Name.");
        }	
    }
    
    public void removeClientById(ClientId id) {
    	
    	if(id == null) {
    		throw new NoSuchElementException("The ID is null");
    	} else {
    		if(clientRepository.get(id) != null) {
    			clientRepository.delete(id);
    		}
    		else {
    			throw new NoSuchElementException("Client not found with this ID");
    		}
    	}
    		
    }
    
    public void removeClientByCpf(Cpf cpf) {
    	
    	if(cpf == null) {
    		throw new NoSuchElementException("The CPF is null");
    	} else {
    		if(clientRepository.get(cpf) != null) {
    			clientRepository.delete(cpf);
    		}
    		else {
    			throw new NoSuchElementException("Client not found with this CPF");
    		}
    	}
    		
    }
    
    public void updateClient(Client client) {
        if (client != null) {
        	clientRepository.update(client);
        } else {
            throw new NoSuchElementException("Client must not be null");
        }
    }
    
}