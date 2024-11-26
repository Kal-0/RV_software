package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.testresult.TestResult;
import domain.entities.testresult.TestResultId;
import domain.services.TestResultService;
import infrastructure.persistence.jpa.TestResultJPA;
import infrastructure.persistence.jpa.JPAMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test-results")
public class TestResultController {

    @Autowired
    private JPAMapper mapper;

    @Autowired
    private TestResultService testResultService;

    /**
     * Save a new TestResult.
     */
    @PostMapping
    public ResponseEntity<TestResultJPA> save(@RequestBody TestResultJPA testResultJPA) {
        TestResult testResult = mapper.map(testResultJPA, TestResult.class);
        TestResult savedTestResult = testResultService.save(testResult);
        TestResultJPA response = mapper.map(savedTestResult, TestResultJPA.class);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

    /**
     * Retrieve a TestResult by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TestResultJPA> getById(@PathVariable("id") int id) {
        TestResultId testResultId = new TestResultId(id);
        TestResult testResult = testResultService.getById(testResultId);
        TestResultJPA response = mapper.map(testResult, TestResultJPA.class);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    /**
     * Retrieve all TestResults.
     */
    @GetMapping
    public ResponseEntity<List<TestResultJPA>> getAll() {
        List<TestResult> testResults = testResultService.getAll();
        List<TestResultJPA> response = new ArrayList<>();

        for (TestResult testResult : testResults) {
            response.add(mapper.map(testResult, TestResultJPA.class));
        }

        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    /**
     * Update an existing TestResult.
     */
    @PutMapping
    public ResponseEntity<TestResultJPA> update(@RequestBody TestResultJPA testResultJPA) {
        TestResult testResult = mapper.map(testResultJPA, TestResult.class);
        testResultService.update(testResult);
        TestResult updatedTestResult = testResultService.getById(testResult.getId());
        TestResultJPA response = mapper.map(updatedTestResult, TestResultJPA.class);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    /**
     * Delete a TestResult by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        TestResultId testResultId = new TestResultId(id);
        testResultService.deleteById(testResultId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
