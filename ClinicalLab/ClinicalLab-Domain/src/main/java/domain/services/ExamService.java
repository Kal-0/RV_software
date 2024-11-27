package domain.services;

import java.util.List;
import java.util.NoSuchElementException;

import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;

public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

 
    public Exam save(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("Exam must not be null.");
        }

        List<Exam> existingExams = examRepository.getAll();
        boolean examExists = existingExams.stream()
                .anyMatch(e -> e.getName().equalsIgnoreCase(exam.getName()));
        if (examExists) {
            throw new IllegalArgumentException("An exam with the same name already exists.");
        }
        boolean idExists = existingExams.stream()
                .anyMatch(e -> e.getId().equals(exam.getId()));
        if (idExists) {
            throw new IllegalArgumentException("An exam with the same ID already exists.");
        }

        examRepository.save(exam);
        return examRepository.get(exam.getId());
    }

    public Exam getById(ExamId id) {
        if (id == null) {
            throw new IllegalArgumentException("ExamId must not be null.");
        }

        Exam exam = examRepository.get(id);
        if (exam == null) {
            throw new NoSuchElementException("No exam found with the given ID.");
        }
        return exam;
    }


    public List<Exam> getAll() {
        List<Exam> exams = examRepository.getExamAll();
        if (exams == null || exams.isEmpty()) {
            throw new NoSuchElementException("No exams found.");
        }
        return exams;
    }

    public void update(Exam exam) {
        if (exam == null || exam.getId() == null) {
            throw new IllegalArgumentException("Exam and its ID must not be null.");
        }

        Exam existingExam = examRepository.get(exam.getId());
        if (existingExam == null) {
            throw new NoSuchElementException("No exam found with the given ID.");
        }

        examRepository.update(exam);
    }

    public void deleteById(ExamId id) {
        if (id == null) {
            throw new IllegalArgumentException("ExamId must not be null.");
        }

        Exam existingExam = examRepository.get(id);
        if (existingExam == null) {
            throw new NoSuchElementException("No exam found with the given ID.");
        }

        examRepository.delete(id);
    }
}
