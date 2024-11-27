package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import domain.entities.client.ClientId;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examtest.ExamTestId;
import domain.services.ExamRequestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exam-request")
public class ExamRequestController {

    @Autowired
    private ExamRequestService examRequestService;

    @PostMapping
    public ResponseEntity<ExamRequestDTO> register(@RequestBody ExamRequestDTO examRequestDTO) {
        ExamRequest examRequest = ExamRequestMapper.toDomain(examRequestDTO);
        examRequest = examRequestService.saveExamRequest(examRequest);
        ExamRequestDTO response = ExamRequestMapper.toDTO(examRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamRequestDTO> getExamRequestById(@PathVariable("id") int id) {
        ExamRequestId examRequestId = new ExamRequestId(id);
        ExamRequest examRequest = examRequestService.getById(examRequestId);
        ExamRequestDTO response = ExamRequestMapper.toDTO(examRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ExamRequestDTO>> findAll() {
        List<ExamRequest> examRequests = examRequestService.getAll();
        List<ExamRequestDTO> responseList = new ArrayList<>();
        for (ExamRequest examRequest : examRequests) {
            responseList.add(ExamRequestMapper.toDTO(examRequest));
        }
        return ResponseEntity.ok(responseList);
    }

    @PutMapping
    public ResponseEntity<ExamRequestDTO> updateExamRequest(@RequestBody ExamRequestDTO examRequestDTO) {
        ExamRequest examRequest = ExamRequestMapper.toDomain(examRequestDTO);
        examRequest = examRequestService.update(examRequest);
        ExamRequestDTO response = ExamRequestMapper.toDTO(examRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamRequest(@PathVariable("id") int id) {
        ExamRequestId examRequestId = new ExamRequestId(id);
        examRequestService.deleteById(examRequestId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Classe DTO para representar ExamRequest
     */
    public static class ExamRequestDTO {
        public int examRequestId;
        public int clientId;
        public List<String> examTestList;
        public LocalDate requestDate;
        public Double totalPrice;
        public String paymentMethod;
        public String status;

        public ExamRequestDTO() {}

        public ExamRequestDTO(int examRequestId, int clientId, List<String> examTestList, LocalDate requestDate,
                              Double totalPrice, String paymentMethod, String status) {
            this.examRequestId = examRequestId;
            this.clientId = clientId;
            this.examTestList = examTestList;
            this.requestDate = requestDate;
            this.totalPrice = totalPrice;
            this.paymentMethod = paymentMethod;
            this.status = status;
        }
    }

    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class ExamRequestMapper {

        public static ExamRequest toDomain(ExamRequestDTO dto) {
            return new ExamRequest(
                new ExamRequestId(dto.examRequestId),
                new ClientId(dto.clientId),
                dto.examTestList != null 
                    ? dto.examTestList.stream().map(id -> new ExamTestId(Integer.parseInt(id))).collect(Collectors.toList()) 
                    : List.of(), // Use uma lista vazia se for nulo
                dto.requestDate,
                dto.totalPrice,
                dto.paymentMethod,
                dto.status
            );
        }

        public static ExamRequestDTO toDTO(ExamRequest domain) {
            return new ExamRequestDTO(
                domain.getExamRequestId().getId(),
                domain.getClientId().getId(),
                domain.getExamTestList() != null 
                    ? domain.getExamTestList().stream().map(id -> String.valueOf(id.getId())).collect(Collectors.toList()) 
                    : List.of(), // Use uma lista vazia se for nulo
                domain.getRequestDate(),
                domain.getTotalPrice(),
                domain.getPaymentMethod(),
                domain.getStatus()
            );
        }
    }
}
