package domain.services;

import java.util.NoSuchElementException;
import java.util.List;

import domain.entities.servicenumber.ServiceNumber;
import domain.entities.servicenumber.ServiceNumberId;
import domain.entities.servicenumber.ServiceNumberRepository;

public class ServiceNumberService {

    private ServiceNumberRepository serviceNumberRepository;

    public ServiceNumberService(ServiceNumberRepository repository) {
        this.serviceNumberRepository = repository;
    }


    public ServiceNumber save(ServiceNumber serviceNumber) {
        if (serviceNumber == null) {
            throw new IllegalArgumentException("ServiceNumber must not be null.");
        }

        if (serviceNumber.getStatus() == null || serviceNumber.getStatus().isEmpty()) {
            serviceNumber.setStatus("PENDING");
        }

        serviceNumberRepository.save(serviceNumber);
        return serviceNumberRepository.get(serviceNumber.getId());
    }


    public ServiceNumber getById(ServiceNumberId id) {
        if (id == null) {
            throw new IllegalArgumentException("ServiceNumberId must not be null.");
        }

        ServiceNumber serviceNumber = serviceNumberRepository.get(id);
        if (serviceNumber == null) {
            throw new NoSuchElementException("No ServiceNumber found with the given ID.");
        }
        return serviceNumber;
    }


    public void update(ServiceNumber serviceNumber) {
        if (serviceNumber == null || serviceNumber.getId() == null) {
            throw new IllegalArgumentException("ServiceNumber and its ID must not be null.");
        }

        ServiceNumber existingServiceNumber = serviceNumberRepository.get(serviceNumber.getId());
        if (existingServiceNumber == null) {
            throw new NoSuchElementException("No ServiceNumber found with the given ID.");
        }

        serviceNumberRepository.update(serviceNumber);
    }


    public void deleteById(ServiceNumberId id) {
        if (id == null) {
            throw new IllegalArgumentException("ServiceNumberId must not be null.");
        }

        ServiceNumber existingServiceNumber = serviceNumberRepository.get(id);
        if (existingServiceNumber == null) {
            throw new NoSuchElementException("No ServiceNumber found with the given ID.");
        }

        serviceNumberRepository.delete(id);
    }
    
    public List<ServiceNumber> getAll() {
        List<ServiceNumber> serviceNumbers = serviceNumberRepository.getAll();
        if (serviceNumbers == null || serviceNumbers.isEmpty()) {
            throw new NoSuchElementException("No ServiceNumbers found.");
        }
        return serviceNumbers;
    }
}
