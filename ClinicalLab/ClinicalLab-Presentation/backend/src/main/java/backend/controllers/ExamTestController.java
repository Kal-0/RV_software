package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.examtest.ExamTest;
import domain.entities.examtest.ExamTestId;
import domain.entities.exam.ExamId;
import domain.entities.testresult.TestResultId;
import domain.services.ExamTestService;

import java.util.ArrayList;
import java.util.List;

@RestController("controlador_exames_teste")
@RequestMapping("/exam-tests")
public class ExamTestController {

    @Autowired
    private ExamTestService examTestService;

    /**
     * Save a new ExamTest.
     */
    @PostMapping
    public ResponseEntity<ExamTestDTO> save(@RequestBody ExamTestDTO examTestDTO) {
        ExamTest examTest = ExamTestMapper.toDomain(examTestDTO);
        ExamTest savedExamTest = examTestService.save(examTest);
        ExamTestDTO response = ExamTestMapper.toDTO(savedExamTest);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Retrieve an ExamTest by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExamTestDTO> getById(@PathVariable("id") int id) {
        ExamTestId examTestId = new ExamTestId(id);
        ExamTest examTest = examTestService.getById(examTestId);
        ExamTestDTO response = ExamTestMapper.toDTO(examTest);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieve all ExamTests.
     */
    @GetMapping
    public ResponseEntity<List<ExamTestDTO>> getAll() {
        List<ExamTest> examTests = examTestService.getAll();
        List<ExamTestDTO> responseList = new ArrayList<>();

        for (ExamTest examTest : examTests) {
            responseList.add(ExamTestMapper.toDTO(examTest));
        }

        return ResponseEntity.ok(responseList);
    }

    /**
     * Update an existing ExamTest.
     */
    @PutMapping
    public ResponseEntity<ExamTestDTO> update(@RequestBody ExamTestDTO examTestDTO) {
        ExamTest examTest = ExamTestMapper.toDomain(examTestDTO);
        examTestService.update(examTest);
        ExamTest updatedExamTest = examTestService.getById(examTest.getId());
        ExamTestDTO response = ExamTestMapper.toDTO(updatedExamTest);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete an ExamTest by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        ExamTestId examTestId = new ExamTestId(id);
        examTestService.deleteById(examTestId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Classe DTO para representar ExamTest
     */
    public static class ExamTestDTO {
        public int id;
        public int examId;
        public Integer testResultId; // Pode ser nulo
        public String status;

        public ExamTestDTO() {}

        public ExamTestDTO(int id, int examId, Integer testResultId, String status) {
            this.id = id;
            this.examId = examId;
            this.testResultId = testResultId;
            this.status = status;
        }
    }

    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class ExamTestMapper {

        public static ExamTest toDomain(ExamTestDTO dto) {
            return new ExamTest(
                new ExamTestId(dto.id),
                new ExamId(dto.examId),
                dto.testResultId != null ? new TestResultId(dto.testResultId) : null,
                dto.status
            );
        }

        public static ExamTestDTO toDTO(ExamTest domain) {
            return new ExamTestDTO(
                domain.getId().getId(),
                domain.getExamId().getId(),
                domain.getTestResultId() != null ? domain.getTestResultId().getId() : null,
                domain.getStatus()
            );
        }
    }
}
