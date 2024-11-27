package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.services.ExamService;

import java.util.ArrayList;
import java.util.List;

@RestController("controlador_exames")
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<ExamDTO> save(@RequestBody ExamDTO examDTO) {
        Exam exam = ExamMapper.toDomain(examDTO);
        exam = examService.save(exam);
        ExamDTO response = ExamMapper.toDTO(exam);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getById(@PathVariable("id") int id) {
        ExamId examId = new ExamId(id);
        Exam exam = examService.getById(examId);
        ExamDTO response = ExamMapper.toDTO(exam);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAll() {
        List<Exam> exams = examService.getAll();
        List<ExamDTO> examsDTO = new ArrayList<>();

        for (Exam exam : exams) {
            examsDTO.add(ExamMapper.toDTO(exam));
        }

        return ResponseEntity.ok(examsDTO);
    }

    @PutMapping
    public ResponseEntity<ExamDTO> update(@RequestBody ExamDTO examDTO) {
        Exam exam = ExamMapper.toDomain(examDTO);
        examService.update(exam);
        Exam updatedExam = examService.getById(exam.getId());
        ExamDTO response = ExamMapper.toDTO(updatedExam);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExamDTO> deleteById(@PathVariable("id") int id) {
        ExamId examId = new ExamId(id);
        Exam exam = examService.getById(examId);
        ExamDTO response = ExamMapper.toDTO(exam);
        examService.deleteById(examId);
        return ResponseEntity.ok(response); // Retorna 200 OK com os dados deletados
    }

    /**
     * Classe DTO para representar Exam
     */
    public static class ExamDTO {
        public int id;
        public String name;
        public String requirements;
        public Double price;
        public int analysisTime;

        public ExamDTO() {}

        public ExamDTO(int id, String name, String requirements, Double price, int analysisTime) {
            this.id = id;
            this.name = name;
            this.requirements = requirements;
            this.price = price;
            this.analysisTime = analysisTime;
        }
    }

    /**
     * Classe Mapper para converter entre DTO e Entidade do Domínio
     */
    public static class ExamMapper {

        public static Exam toDomain(ExamDTO dto) {
            return new Exam(
                new ExamId(dto.id), // Converte o ID do DTO para o domínio
                dto.name,           // Nome do exame
                dto.requirements,   // Requisitos
                dto.price,          // Preço
                dto.analysisTime    // Tempo de análise
            );
        }

        public static ExamDTO toDTO(Exam domain) {
            return new ExamDTO(
                domain.getId().getId(), // Converte o ID do domínio para o DTO
                domain.getName(),       // Nome do exame
                domain.getRequirements(), // Requisitos
                domain.getPrice(),        // Preço
                domain.getAnalysisTime()  // Tempo de análise
            );
        }
    }
}
