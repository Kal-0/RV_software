package infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entities.testresult.TestResult;
import domain.entities.testresult.TestResultId;
import domain.entities.testresult.TestResultRepository;
import infrastructure.persistence.jpa.repository.TestResultJPARepository;

@Entity
@Table(name = "TestResult")
public class TestResultJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testResultId;

    @Column(name = "resultDate", nullable = false)
    private LocalDate resultDate;

    @Column(name = "resultContent")
    private String resultContent;
    

	public Integer getTestResultId() {
		return testResultId;
	}

	public void setTestResultId(Integer testResultId) {
		this.testResultId = testResultId;
	}

	public LocalDate getResultDate() {
		return resultDate;
	}

	public void setResultDate(LocalDate resultDate) {
		this.resultDate = resultDate;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}
    
}

@Repository
class TestResultRepositoryImpl implements TestResultRepository {

    @Autowired
    private TestResultJPARepository testResultJPARepository;

    @Autowired
    private JPAMapper mapper;

    @Override
    public void save(TestResult testResult) {
        TestResultJPA testResultJPA = mapper.map(testResult, TestResultJPA.class);
        testResultJPARepository.save(testResultJPA);
    }

    @Override
    public void delete(TestResultId testResultId) {
    	
        testResultJPARepository.deleteById(testResultId.getId());
    }

    @Override
    public TestResult get(TestResultId testResultId) {
        TestResultJPA testResultJPA = testResultJPARepository.findById(testResultId.getId()).orElse(null);
        return mapper.map(testResultJPA, TestResult.class);
    }

    @Override
    public void update(TestResult testResult) {
        TestResultJPA testResultJPA = mapper.map(testResult, TestResultJPA.class);
        testResultJPARepository.save(testResultJPA);
    }
}
