package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.testresult.TestResult;
import domain.entities.testresult.TestResultId;
import domain.services.TestResultService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test-results")
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    @PostMapping
    public ResponseEntity<TestResultDTO> save(@RequestBody TestResultDTO testResultDTO) {
        TestResult testResult = TestResultMapper.toDomain(testResultDTO);
        TestResult savedTestResult = testResultService.save(testResult);
        TestResultDTO response = TestResultMapper.toDTO(savedTestResult);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResultDTO> getById(@PathVariable("id") int id) {
        TestResultId testResultId = new TestResultId(id);
        TestResult testResult = testResultService.getById(testResultId);
        TestResultDTO response = TestResultMapper.toDTO(testResult);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TestResultDTO>> getAll() {
        List<TestResult> testResults = testResultService.getAll();
        List<TestResultDTO> response = new ArrayList<>();
        for (TestResult testResult : testResults) {
            response.add(TestResultMapper.toDTO(testResult));
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<TestResultDTO> update(@RequestBody TestResultDTO testResultDTO) {
        TestResult testResult = TestResultMapper.toDomain(testResultDTO);
        testResultService.update(testResult);
        TestResult updatedTestResult = testResultService.getById(testResult.getId());
        TestResultDTO response = TestResultMapper.toDTO(updatedTestResult);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        TestResultId testResultId = new TestResultId(id);
        testResultService.deleteById(testResultId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Classe DTO para representar TestResult
     */
    public static class TestResultDTO {
        public int id;
        public LocalDate resultDate;
        public String resultContent;

        public TestResultDTO() {}

        public TestResultDTO(int id, LocalDate resultDate, String resultContent) {
            this.id = id;
            this.resultDate = resultDate;
            this.resultContent = resultContent;
        }
    }

    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class TestResultMapper {

        public static TestResult toDomain(TestResultDTO dto) {
            return new TestResult(
                new TestResultId(dto.id),
                dto.resultDate,
                dto.resultContent
            );
        }

        public static TestResultDTO toDTO(TestResult domain) {
            return new TestResultDTO(
                domain.getId().getId(),
                domain.getResultDate(),
                domain.getResultContent()
            );
        }
    }
}
