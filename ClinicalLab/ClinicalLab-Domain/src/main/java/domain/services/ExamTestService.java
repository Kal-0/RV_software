package domain.services;

import java.util.List;
import java.util.NoSuchElementException;

import domain.entities.examtest.ExamTest;
import domain.entities.examtest.ExamTestId;
import domain.entities.examtest.ExamTestRepository;

public class ExamTestService {

    private final ExamTestRepository examTestRepository;

    public ExamTestService(ExamTestRepository examTestRepository) {
        this.examTestRepository = examTestRepository;
    }

    /**
     * Save a new ExamTest.
     */
    public ExamTest save(ExamTest examTest) {
        if (examTest == null) {
            throw new IllegalArgumentException("ExamTest must not be null.");
        }

        // Verificar duplicidade de TestResultId (//Tem que verificar isso aqui)
        if (examTest.getTestResultId() != null) {
            List<ExamTest> allExamTests = examTestRepository.getExamTestAll();
            boolean isDuplicate = allExamTests.stream()
                .anyMatch(existingTest -> existingTest.getTestResultId() != null &&
                                          existingTest.getTestResultId().equals(examTest.getTestResultId()));
            if (isDuplicate) {
                throw new IllegalArgumentException("A TestResult with the given ID already exists.");
            }
        }

        examTestRepository.save(examTest);
        return examTestRepository.get(examTest.getId());
    }

    /**
     * Retrieve an ExamTest by its ID.
     */
    public ExamTest getById(ExamTestId id) {
        if (id == null) {
            throw new IllegalArgumentException("ExamTestId must not be null.");
        }

        ExamTest examTest = examTestRepository.get(id);
        if (examTest == null) {
            throw new NoSuchElementException("No ExamTest found with the given ID.");
        }
        return examTest;
    }

    /**
     * Retrieve all ExamTests.
     */
    public List<ExamTest> getAll() {
        List<ExamTest> examTests = examTestRepository.getExamTestAll();
        if (examTests == null || examTests.isEmpty()) {
            throw new NoSuchElementException("No ExamTests found.");
        }
        return examTests;
    }

    /**
     * Update an existing ExamTest.
     */
    public void update(ExamTest examTest) {
        if (examTest == null || examTest.getId() == null) {
            throw new IllegalArgumentException("ExamTest and its ID must not be null.");
        }

        ExamTest existingExamTest = examTestRepository.get(examTest.getId());
        if (existingExamTest == null) {
            throw new NoSuchElementException("No ExamTest found with the given ID.");
        }

        examTestRepository.update(examTest);
    }

    /**
     * Delete an ExamTest by its ID.
     */
    public void deleteById(ExamTestId id) {
        if (id == null) {
            throw new IllegalArgumentException("ExamTestId must not be null.");
        }

        ExamTest existingExamTest = examTestRepository.get(id);
        if (existingExamTest == null) {
            throw new NoSuchElementException("No ExamTest found with the given ID.");
        }

        examTestRepository.delete(id);
    }
}
