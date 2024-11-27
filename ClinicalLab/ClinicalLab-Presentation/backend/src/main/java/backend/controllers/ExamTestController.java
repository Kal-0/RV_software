package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.examtest.ExamTest;
import domain.entities.examtest.ExamTestId;
import domain.services.ExamTestService;
import infrastructure.persistence.jpa.ExamTestJPA;
import infrastructure.persistence.jpa.JPAMapper;

import java.util.ArrayList;
import java.util.List;

@RestController("controlador_exames_teste")
@RequestMapping("/exam-tests")
public class ExamTestController {

    @Autowired
    private JPAMapper mapper;

    @Autowired
    private ExamTestService examTestService;

    /**
     * Save a new ExamTest.
     */
    @PostMapping
    public ResponseEntity<ExamTestJPA> save(@RequestBody ExamTestJPA examTestJPA) {
        ExamTest examTest = mapper.map(examTestJPA, ExamTest.class);
        ExamTest savedExamTest = examTestService.save(examTest);
        ExamTestJPA response = mapper.map(savedExamTest, ExamTestJPA.class);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

    /**
     * Retrieve an ExamTest by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExamTestJPA> getById(@PathVariable("id") int id) {
        ExamTestId examTestId = new ExamTestId(id);
        ExamTest examTest = examTestService.getById(examTestId);
        ExamTestJPA response = mapper.map(examTest, ExamTestJPA.class);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    /**
     * Retrieve all ExamTests.
     */
    @GetMapping
    public ResponseEntity<List<ExamTestJPA>> getAll() {
        List<ExamTest> examTests = examTestService.getAll();
        List<ExamTestJPA> response = new ArrayList<>();

        for (ExamTest examTest : examTests) {
            response.add(mapper.map(examTest, ExamTestJPA.class));
        }

        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    /**
     * Update an existing ExamTest.
     */
    @PutMapping
    public ResponseEntity<ExamTestJPA> update(@RequestBody ExamTestJPA examTestJPA) {
        ExamTest examTest = mapper.map(examTestJPA, ExamTest.class);
        examTestService.update(examTest);
        ExamTest updatedExamTest = examTestService.getById(examTest.getId());
        ExamTestJPA response = mapper.map(updatedExamTest, ExamTestJPA.class);
        return ResponseEntity.ok(response); // Retorna 200 OK
    }

    /**
     * Delete an ExamTest by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        ExamTestId examTestId = new ExamTestId(id);
        examTestService.deleteById(examTestId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
