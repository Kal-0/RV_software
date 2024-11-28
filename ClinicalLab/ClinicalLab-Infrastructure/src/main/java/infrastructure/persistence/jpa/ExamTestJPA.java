package infrastructure.persistence.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

import domain.entities.examtest.ExamTest;
import domain.entities.examtest.ExamTestId;
import domain.entities.examtest.ExamTestRepository;
import infrastructure.persistence.jpa.repository.ExamTestJPARepository;
import jakarta.persistence.*;

@Entity
@Table(name = "Exam_Test")
public class ExamTestJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer examTestId;

    @ManyToOne
    @JoinColumn(name = "Exam_Id")
    private ExamJPA exam;

    @OneToOne
    @JoinColumn(name = "TestResult_Id", nullable = true)
    private TestResultJPA testResult;

    @Column(name = "status")
    private String status;

	public Integer getExamTestId() {
		return examTestId;
	}

	public void setExamTestId(Integer examTestId) {
		this.examTestId = examTestId;
	}

	public ExamJPA getExam() {
		return exam;
	}

	public void setExam(ExamJPA exam) {
		this.exam = exam;
	}

	public TestResultJPA getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResultJPA testResult) {
		this.testResult = testResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}

@Repository
class ExamTestRepositoryImpl implements ExamTestRepository {

    @Autowired
    private ExamTestJPARepository examTestJPARepository;

    @Autowired
    private JPAMapper mapper;
    


    @Override
    public ExamTest save(ExamTest examTest) {
        ExamTestJPA examTestJPA = mapper.map(examTest, ExamTestJPA.class);
        examTestJPA = examTestJPARepository.save(examTestJPA);
        examTest.setId(new ExamTestId(examTestJPA.getExamTestId()));
        return examTest;
    }

    @Override
    public void delete(ExamTestId id) {
        examTestJPARepository.deleteById(id.getId());
    }

    @Override
    public ExamTest get(ExamTestId id) {
        ExamTestJPA examTestJPA = examTestJPARepository.findById(id.getId()).orElse(null);
        return mapper.map(examTestJPA, ExamTest.class);
    }

    @Override
    public void update(ExamTest examTest) {
        ExamTestJPA examTestJPA = mapper.map(examTest, ExamTestJPA.class);
        examTestJPARepository.save(examTestJPA);
    }
    
    @Override
    public List<ExamTest> getExamTestAll() {
        List<ExamTestJPA> examsTestJPA = examTestJPARepository.findAll();
        return examsTestJPA.stream()
                .map(examTestJPA -> mapper.map(examTestJPA, ExamTest.class))
                .collect(Collectors.toList());
    }
}