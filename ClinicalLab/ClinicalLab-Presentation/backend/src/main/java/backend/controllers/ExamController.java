package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.services.ExamService;
import infrastructure.persistence.jpa.ExamJPA;
import infrastructure.persistence.jpa.JPAMapper;

import java.util.ArrayList;
import java.util.List;

@RestController("controlador_exames")
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private JPAMapper mapper;

    @Autowired
    private ExamService examService;


    @PostMapping
    public ResponseEntity<ExamJPA> save(@RequestBody ExamJPA examJPA) {
        Exam exam = mapper.map(examJPA, Exam.class);
        exam = examService.save(exam);
        examJPA = mapper.map(exam, ExamJPA.class);
        return ResponseEntity.status(201).body(examJPA); // Retorna 201 Created
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExamJPA> getById(@PathVariable("id") int id) {
        ExamId examId = new ExamId(id);
        Exam exam = examService.getById(examId);
        ExamJPA examJPA = mapper.map(exam, ExamJPA.class);
        return ResponseEntity.ok(examJPA);
    }


    @GetMapping
    public ResponseEntity<List<ExamJPA>> getAll() {
        List<Exam> exams = examService.getAll();
        List<ExamJPA> examsJPA = new ArrayList<>();

        for (Exam exam : exams) {
            examsJPA.add(mapper.map(exam, ExamJPA.class));
        }

        return ResponseEntity.ok(examsJPA);
    }


    @PutMapping
    public ResponseEntity<ExamJPA> update(@RequestBody ExamJPA examJPA) {
        Exam exam = mapper.map(examJPA, Exam.class);
        examService.update(exam);
        exam = examService.getById(exam.getId());
        examJPA = mapper.map(exam, ExamJPA.class);
        return ResponseEntity.ok(examJPA);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ExamJPA> deleteById(@PathVariable("id") int id) {
        ExamId examId = new ExamId(id);
        Exam exam = examService.getById(examId);
        ExamJPA examJPA = mapper.map(exam, ExamJPA.class);
        examService.deleteById(examId);
        return ResponseEntity.ok(examJPA); // Retorna 200 OK com os dados deletados
    }
    
    
}
