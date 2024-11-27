package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.servicenumber.ServiceNumber;
import domain.entities.servicenumber.ServiceNumberId;
import domain.services.ServiceNumberService;

import java.util.ArrayList;
import java.util.List;

@RestController("controlador_service_number")
@RequestMapping("/service-numbers")
public class ServiceNumberController {

    @Autowired
    private ServiceNumberService serviceNumberService;

    @PostMapping
    public ResponseEntity<ServiceNumberDTO> save(@RequestBody ServiceNumberDTO serviceNumberDTO) {
        ServiceNumber serviceNumber = ServiceNumberMapper.toDomain(serviceNumberDTO);
        serviceNumber = serviceNumberService.save(serviceNumber);
        ServiceNumberDTO response = ServiceNumberMapper.toDTO(serviceNumber);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceNumberDTO> getById(@PathVariable("id") int id) {
        ServiceNumberId serviceNumberId = new ServiceNumberId(id);
        ServiceNumber serviceNumber = serviceNumberService.getById(serviceNumberId);
        ServiceNumberDTO response = ServiceNumberMapper.toDTO(serviceNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ServiceNumberDTO>> getAll() {
        List<ServiceNumber> serviceNumbers = serviceNumberService.getAll();
        List<ServiceNumberDTO> responseList = new ArrayList<>();

        for (ServiceNumber serviceNumber : serviceNumbers) {
            responseList.add(ServiceNumberMapper.toDTO(serviceNumber));
        }

        return ResponseEntity.ok(responseList);
    }

    @PutMapping
    public ResponseEntity<ServiceNumberDTO> update(@RequestBody ServiceNumberDTO serviceNumberDTO) {
        ServiceNumber serviceNumber = ServiceNumberMapper.toDomain(serviceNumberDTO);
        serviceNumberService.update(serviceNumber);
        ServiceNumber updatedServiceNumber = serviceNumberService.getById(serviceNumber.getId());
        ServiceNumberDTO response = ServiceNumberMapper.toDTO(updatedServiceNumber);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceNumberDTO> deleteById(@PathVariable("id") int id) {
        ServiceNumberId serviceNumberId = new ServiceNumberId(id);
        ServiceNumber serviceNumber = serviceNumberService.getById(serviceNumberId);
        ServiceNumberDTO response = ServiceNumberMapper.toDTO(serviceNumber);
        serviceNumberService.deleteById(serviceNumberId);
        return ResponseEntity.ok(response);
    }

    /**
     * Classe DTO para representar ServiceNumber
     */
    public static class ServiceNumberDTO {
        public int id;
        public String number;
        public boolean isPriority;
        public String status;

        public ServiceNumberDTO() {}

        public ServiceNumberDTO(int id, String number, boolean isPriority, String status) {
            this.id = id;
            this.number = number;
            this.isPriority = isPriority;
            this.status = status;
        }
    }

    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class ServiceNumberMapper {

        public static ServiceNumber toDomain(ServiceNumberDTO dto) {
            return new ServiceNumber(
                new ServiceNumberId(dto.id),
                dto.number,
                dto.isPriority,
                dto.status
            );
        }

        public static ServiceNumberDTO toDTO(ServiceNumber domain) {
            return new ServiceNumberDTO(
                domain.getId() != null ? domain.getId().getId() : 0, // Verifica se o ID é nulo
                domain.getNumber(),
                domain.isPriority(),
                domain.getStatus()
            );
        }
    }
}
