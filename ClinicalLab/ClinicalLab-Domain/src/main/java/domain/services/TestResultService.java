package domain.services;

import domain.entities.testresult.TestResult;
import domain.entities.testresult.TestResultId;
import domain.entities.testresult.TestResultRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TestResultService {

    private final TestResultRepository testResultRepository;

    public TestResultService(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

   
    public TestResult save(TestResult testResult) {
        if (testResult == null) {
            throw new IllegalArgumentException("TestResult must not be null.");
        }

        if (testResult.getId() != null) {
            TestResult existing = testResultRepository.get(testResult.getId());
            if (existing != null) {
                throw new IllegalArgumentException("A TestResult with the given ID already exists.");
            }
        }

        
        testResultRepository.save(testResult);
        return testResultRepository.get(testResult.getId());
    }

    
    public TestResult getById(TestResultId id) {
        if (id == null) {
            throw new IllegalArgumentException("TestResultId must not be null.");
        }

        TestResult testResult = testResultRepository.get(id);
        if (testResult == null) {
            throw new NoSuchElementException("No TestResult found with the given ID.");
        }
        return testResult;
    }

   
    public List<TestResult> getAll() {
        List<TestResult> testResults = testResultRepository.getTestResultAll();
        if (testResults == null || testResults.isEmpty()) {
            throw new NoSuchElementException("No TestResults found.");
        }
        return testResults;
    }

    
    public void update(TestResult testResult) {
        if (testResult == null || testResult.getId() == null) {
            throw new IllegalArgumentException("TestResult and its ID must not be null.");
        }

        TestResult existing = testResultRepository.get(testResult.getId());
        if (existing == null) {
            throw new NoSuchElementException("No TestResult found with the given ID.");
        }

        testResultRepository.update(testResult);
    }

    
    public void deleteById(TestResultId id) {
        if (id == null) {
            throw new IllegalArgumentException("TestResultId must not be null.");
        }

        TestResult existing = testResultRepository.get(id);
        if (existing == null) {
            throw new NoSuchElementException("No TestResult found with the given ID.");
        }

        testResultRepository.delete(id);
    }
}
