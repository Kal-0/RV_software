package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.controllers.ExamRequestController.ExamRequestDTO;
import backend.controllers.ExamRequestController.ExamRequestMapper;
import domain.entities.client.ClientId;
import domain.entities.clientservice.ClientServiceId;
import domain.entities.clientservice.ClientServices;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examtest.ExamTestId;
import domain.entities.servicenumber.ServiceNumber;
import domain.services.ClientServiceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client-service")
public class ClientServiceController {

    @Autowired
    private ClientServiceService clientServiceService;

    @PostMapping
    public ResponseEntity<ClientServiceDTO> save(@RequestBody ClientServiceDTO clientServiceDTO) {
        ClientServices clientService = ClientServiceMapper.toDomain(clientServiceDTO);
        clientService = clientServiceService.save(clientService);
        ClientServiceDTO response = ClientServiceMapper.toDTO(clientService);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientServiceDTO> getClientServiceById(@PathVariable("id") int id) {
        ClientServiceId clientServiceId = new ClientServiceId(id);
        ClientServices clientService = clientServiceService.getClientServiceById(clientServiceId);
        ClientServiceDTO response = ClientServiceMapper.toDTO(clientService);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/exam-request")
    public ResponseEntity<ClientServiceDTO> addExamRequestToClientService(
            @PathVariable("id") int id, @RequestBody ExamRequestDTO examRequestDTO) {
        ClientServiceId clientServiceId = new ClientServiceId(id);
        ClientServices clientService = clientServiceService.getClientServiceById(clientServiceId);
        ExamRequest examRequest = ExamRequestMapper.toDomain(examRequestDTO);
        clientServiceService.addExamRequest(clientService, examRequest);
        ClientServiceDTO response = ClientServiceMapper.toDTO(clientService);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientServiceDTO>> getAll() {
        List<ClientServices> clientServices = clientServiceService.getAll();
        List<ClientServiceDTO> clientServiceDTOs = new ArrayList<>();

        for (ClientServices service : clientServices) {
            clientServiceDTOs.add(ClientServiceMapper.toDTO(service));
        }

        return ResponseEntity.ok(clientServiceDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        ClientServiceId clientServiceId = new ClientServiceId(id);
        clientServiceService.delete(clientServiceId);
        return ResponseEntity.accepted().body("Client Service with ID " + id + " has been successfully deleted.");
    }

    @PutMapping
    public ResponseEntity<ClientServiceDTO> update(@RequestBody ClientServiceDTO clientServiceDTO) {
        ClientServices clientService = ClientServiceMapper.toDomain(clientServiceDTO);
        clientServiceService.update(clientService);
        ClientServices updatedService = clientServiceService.getClientServiceById(clientService.getId());
        ClientServiceDTO response = ClientServiceMapper.toDTO(updatedService);
        return ResponseEntity.ok(response);
    }

    /**
     * Classe DTO para representar ClientServices e ExamRequest
     */
    public static class ClientServiceDTO {
        public int clientServiceId;
        public String serviceNumber;
        public boolean isPriority;
        public String serviceStatus;
        public int examRequestId;
        public String status;

        public ClientServiceDTO() {}

        public ClientServiceDTO(
                int clientServiceId,
                String serviceNumber,
                boolean isPriority,
                String serviceStatus,
                int examRequestId,
                String status
        ) {
            this.clientServiceId = clientServiceId;
            this.serviceNumber = serviceNumber;
            this.isPriority = isPriority;
            this.serviceStatus = serviceStatus;
            this.examRequestId = examRequestId;
            this.status = status;
        }
    }


    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class ClientServiceMapper {

        public static ClientServices toDomain(ClientServiceDTO dto) {
            ServiceNumber serviceNumber = new ServiceNumber(
                    dto.serviceNumber,
                    dto.isPriority
            );
            serviceNumber.setStatus(dto.serviceStatus);

            // Usa apenas o ID para criar a entidade ClientServices
            return new ClientServices(
                    new ClientServiceId(dto.clientServiceId),
                    serviceNumber,
                    dto.examRequestId != 0 ? new ExamRequestId(dto.examRequestId) : null,
                    dto.status
            );
        }

        public static ClientServiceDTO toDTO(ClientServices domain) {
            return new ClientServiceDTO(
                    domain.getId().getId(),
                    domain.getServiceNumber() != null ? domain.getServiceNumber().getNumber() : null,
                    domain.getServiceNumber() != null && domain.getServiceNumber().isPriority(),
                    domain.getServiceNumber() != null ? domain.getServiceNumber().getStatus() : null,
                    domain.getExamRequestId() != null ? domain.getExamRequestId().getId() : 0,
                    domain.getStatus()
            );
        }
    }



}
