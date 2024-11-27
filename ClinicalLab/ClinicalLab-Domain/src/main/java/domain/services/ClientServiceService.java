package domain.services;

import java.util.List;
import java.util.NoSuchElementException;

import domain.entities.clientservice.ClientServiceId;
import domain.entities.clientservice.ClientServiceRepository;
import domain.entities.clientservice.ClientServices;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;


public class ClientServiceService {

private ClientServiceRepository clientServiceRepository;

    public ClientServiceService(ClientServiceRepository repository) {
        this.clientServiceRepository = repository;
    }
    

    public ClientServices save(ClientServices clientService) {
        if (clientService != null) {
            clientServiceRepository.save(clientService);
            return clientServiceRepository.get(clientService.getId());
        } else {
            throw new NoSuchElementException("ClientService must not be null");
        }
    }

    public ClientServices getClientServiceById(ClientServiceId id) {
        ClientServices clientService = clientServiceRepository.get(id);
        if (clientService != null) {
            return clientService;
        } else {
            throw new NoSuchElementException("No ClientService found with the given ID.");
        }
    }
    
    
    public void addExamRequest(ClientServices clientService, ExamRequest examRequest) {
    clientService.setExamRequestId(examRequest.getExamRequestId());
    }
    
    public void delete(ClientServiceId id) {
        ClientServices clientService = clientServiceRepository.get(id);
        if (clientService != null) {
            clientServiceRepository.delete(id);
        } else {
            throw new NoSuchElementException("No ClientService found with the given ID.");
        }
    }


    public ClientServices update(ClientServices clientService) {
        if (clientService != null && clientServiceRepository.get(clientService.getId()) != null) {
            clientServiceRepository.update(clientService);
            return clientServiceRepository.get(clientService.getId());
        } else {
            throw new NoSuchElementException("ClientService not found for update.");
        }
    }
   
    public List<ClientServices> getAll(){
    List<ClientServices> clientServices = clientServiceRepository.getClientServiceAll();
    if(clientServices == null || clientServices.isEmpty()) {
    throw new NoSuchElementException("ClientService not found.");
    }
    
    return clientServices;
    }
    
}