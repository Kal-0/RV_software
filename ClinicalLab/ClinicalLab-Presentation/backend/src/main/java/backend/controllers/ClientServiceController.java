package backend.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.clientservice.ClientServiceId;
import domain.entities.clientservice.ClientServices;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.services.ClientService;
import domain.services.ClientServiceService;
import infrastructure.persistence.jpa.ClientJPA;
import infrastructure.persistence.jpa.ClientServiceJPA;
import infrastructure.persistence.jpa.JPAMapper;

@RestController
@RequestMapping("/client-service")
public class ClientServiceController {

   @Autowired
   private ClientServiceService clientServiceService;

   @Autowired
   private JPAMapper mapper;
   

  @PostMapping
  public ResponseEntity<ClientServiceJPA> save(@RequestBody ClientServiceJPA clientServiceJPA) {
  ClientServices clientService = mapper.map(clientServiceJPA, ClientServices.class);
  clientService = clientServiceService.save(clientService);
      clientServiceJPA = mapper.map(clientService, ClientServiceJPA.class);
      return ResponseEntity.status(201).body(clientServiceJPA); // Retorna 201 Created
  }

  
  @GetMapping("/{id}")
    public ResponseEntity<ClientServiceJPA> getClientServiceById(@PathVariable("id") int id) {
        ClientServiceId clientServiceId = new ClientServiceId(id);
        ClientServices clientService = clientServiceService.getClientServiceById(clientServiceId);
        ClientServiceJPA clientServiceJPA = mapper.map(clientService, ClientServiceJPA.class);
       return ResponseEntity.ok(clientServiceJPA);
  }

   @PutMapping("/{id}/exam-request")
   public ResponseEntity<ClientServiceJPA> addExamRequestToClientService(
       @PathVariable("id") int id, @RequestBody ExamRequest examRequest) {
       ClientServiceId clientServiceId = new ClientServiceId(id);
       ClientServices clientService = clientServiceService.getClientServiceById(clientServiceId);
       clientServiceService.addExamRequest(clientService, examRequest);
       return null;
   }
  
   @GetMapping
   public ResponseEntity<List<ClientServiceJPA>> getAll() {
       List<ClientServices> clientServices = clientServiceService.getAll();
       List<ClientServiceJPA> clientServicesJPA = new ArrayList<>();

       for (ClientServices service : clientServices) {
           clientServicesJPA.add(mapper.map(service, ClientServiceJPA.class));
       }

       return ResponseEntity.ok(clientServicesJPA);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<String> delete(@PathVariable("id") int id) {
       ClientServiceId clientServiceId = new ClientServiceId(id);
       clientServiceService.delete(clientServiceId);
       return ResponseEntity.accepted().body("Client Service with ID " + id + " has been successfully deleted.");
   }

   @PutMapping
   public ResponseEntity<ClientServiceJPA> update(
           @RequestBody ClientServiceJPA clientServiceJPA) {
       ClientServices clientService = mapper.map(clientServiceJPA, ClientServices.class);
       clientServiceService.update(clientService);
       ClientServices updatedService = clientServiceService.getClientServiceById(clientService.getId());
       ClientServiceJPA updatedServiceJPA = mapper.map(updatedService, ClientServiceJPA.class);
       return ResponseEntity.ok(updatedServiceJPA);
   }

   
}