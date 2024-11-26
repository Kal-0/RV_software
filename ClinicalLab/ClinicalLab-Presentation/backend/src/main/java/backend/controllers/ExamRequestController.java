package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.services.ExamRequestService;
import infrastructure.persistence.jpa.ExamRequestJPA;
import infrastructure.persistence.jpa.JPAMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exam-request")
public class ExamRequestController {

    @Autowired
    private JPAMapper mapper;

    @Autowired
    private ExamRequestService examRequestService;

    @PostMapping
    public ResponseEntity<ExamRequestJPA> register(@RequestBody ExamRequestJPA examRequestJPA) {
        // Mapeia o DTO para a entidade de domínio
        ExamRequest examRequest = mapper.map(examRequestJPA, ExamRequest.class);
        // Salva no serviço
        examRequest = examRequestService.saveExamRequest(examRequest);
        // Mapeia de volta a entidade para o DTO
        examRequestJPA = mapper.map(examRequest, ExamRequestJPA.class);
        return ResponseEntity.status(201).body(examRequestJPA); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamRequestJPA> getExamRequestById(@PathVariable("id") int id) {
        // Cria o identificador da entidade
        ExamRequestId examRequestId = new ExamRequestId(id);
        // Recupera a entidade do serviço
        ExamRequest examRequest = examRequestService.getById(examRequestId);
        // Mapeia a entidade para o DTO
        ExamRequestJPA examRequestJPA = mapper.map(examRequest, ExamRequestJPA.class);
        return ResponseEntity.ok(examRequestJPA);
    }

    @GetMapping
    public ResponseEntity<List<ExamRequestJPA>> findAll() {
        // Recupera todas as entidades do serviço
        List<ExamRequest> examRequests = examRequestService.getAll();
        // Converte para DTOs
        List<ExamRequestJPA> examRequestsJPA = new ArrayList<>();
        for (ExamRequest examRequest : examRequests) {
            examRequestsJPA.add(mapper.map(examRequest, ExamRequestJPA.class));
        }
        return ResponseEntity.ok(examRequestsJPA);
    }

    @PutMapping
    public ResponseEntity<ExamRequestJPA> updateExamRequest(@RequestBody ExamRequestJPA examRequestJPA) {
        // Mapeia o DTO para a entidade de domínio
        ExamRequest examRequest = mapper.map(examRequestJPA, ExamRequest.class);
        // Atualiza no serviço
        examRequest = examRequestService.update(examRequest);
        // Mapeia de volta a entidade para o DTO
        examRequestJPA = mapper.map(examRequest, ExamRequestJPA.class);
        return ResponseEntity.ok(examRequestJPA);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamRequest(@PathVariable("id") int id) {
        // Cria o identificador da entidade
        ExamRequestId examRequestId = new ExamRequestId(id);
        // Remove a entidade no serviço
        examRequestService.deleteById(examRequestId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
