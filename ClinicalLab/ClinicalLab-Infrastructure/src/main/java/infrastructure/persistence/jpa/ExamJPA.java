package infrastructure.persistence.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import infrastructure.persistence.jpa.repository.ExamJPARepository;
import infrastructure.persistence.mappers.ExamMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Exams")
public class ExamJPA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String requirements;
    private Double price;
    private Integer analysisTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAnalysisTime() {
		return analysisTime;
	}
	public void setAnalysisTime(Integer analysisTime) {
		this.analysisTime = analysisTime;
	}
    
}


@Repository
class ExamRepositoryImpl implements ExamRepository {

    @Autowired
    private ExamJPARepository examJPARepository;

    @Autowired
    private ExamMapper examMapper; // Usar o ExamMapper

    @Override
    public void save(Exam exam) {
        ExamJPA examJPA = examMapper.map(exam);
        examJPARepository.save(examJPA);
    }
    
    @Override
    public void delete(ExamId id) {
        examJPARepository.deleteById((long) id.getId());
    }

    @Override
    public Exam get(ExamId id) {
        ExamJPA examJPA = examJPARepository.findById((long) id.getId()).orElse(null);
        return examMapper.map(examJPA);
    }

    @Override
    public void update(Exam exam) {
        ExamJPA examJPA = examMapper.map(exam);
        examJPARepository.save(examJPA);
    }
}


