package backend.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.servicenumber.ServiceNumber;
import domain.entities.servicenumber.ServiceNumberId;
import domain.services.ServiceNumberService;
import infrastructure.persistence.jpa.ServiceNumberJPA;
import infrastructure.persistence.jpa.JPAMapper;
import java.util.ArrayList;
import java.util.List;
@RestController("controlador_service_number")
@RequestMapping("/service-number")
public class ServiceNumberController {
    @Autowired
    private JPAMapper mapper;
    @Autowired
    private ServiceNumberService serviceNumberService;
    @PostMapping
    public ResponseEntity<ServiceNumberJPA> save(@RequestBody ServiceNumberJPA serviceNumberJPA) {
        ServiceNumber serviceNumber = mapper.map(serviceNumberJPA, ServiceNumber.class);
        serviceNumber = serviceNumberService.save(serviceNumber);
        serviceNumberJPA = mapper.map(serviceNumber, ServiceNumberJPA.class);
        return ResponseEntity.status(201).body(serviceNumberJPA); // Retorna 201 Created
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceNumberJPA> getById(@PathVariable("id") int id) {
        ServiceNumberId serviceNumberId = new ServiceNumberId(id);
        ServiceNumber serviceNumber = serviceNumberService.getById(serviceNumberId);
        ServiceNumberJPA serviceNumberJPA = mapper.map(serviceNumber, ServiceNumberJPA.class);
        return ResponseEntity.ok(serviceNumberJPA);
    }
    @GetMapping
    public ResponseEntity<List<ServiceNumberJPA>> getAll() {
        List<ServiceNumber> serviceNumbers = serviceNumberService.getAll();
        List<ServiceNumberJPA> serviceNumbersJPA = new ArrayList<>();
        
        for (ServiceNumber serviceNumber : serviceNumbers) {
            serviceNumbersJPA.add(mapper.map(serviceNumber, ServiceNumberJPA.class));
        }
        
        return ResponseEntity.ok(serviceNumbersJPA);
    }
    @PutMapping
    public ResponseEntity<ServiceNumberJPA> update(@RequestBody ServiceNumberJPA serviceNumberJPA) {
        ServiceNumber serviceNumber = mapper.map(serviceNumberJPA, ServiceNumber.class);
        serviceNumberService.update(serviceNumber);
        serviceNumber = serviceNumberService.getById(serviceNumber.getId());
        serviceNumberJPA = mapper.map(serviceNumber, ServiceNumberJPA.class);
        return ResponseEntity.ok(serviceNumberJPA);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceNumberJPA> deleteById(@PathVariable("id") int id) {
        ServiceNumberId serviceNumberId = new ServiceNumberId(id);
        ServiceNumber serviceNumber = serviceNumberService.getById(serviceNumberId);
        ServiceNumberJPA serviceNumberJPA = mapper.map(serviceNumber, ServiceNumberJPA.class);
        serviceNumberService.deleteById(serviceNumberId);
        return ResponseEntity.ok(serviceNumberJPA); // Retorna 200 OK com os dados deletados
    }
}