package infrastructure.persistence.jpa;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examrequest.ExamRequestRepository;
import infrastructure.persistence.jpa.repository.ExamRequestJPARepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity
@Table(name = "Exam_Request")
public class ExamRequestJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examRequestId;

    @ManyToOne
    @JoinColumn(name = "Client_Id")
    private ClientJPA client;

    @OneToMany
    @JoinColumn(name = "Exams_List_Id")
    private List<ExamTestJPA> examTestList;

    @Column(name = "requestDate", nullable = false)
    private LocalDate requestDate;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    private String status;

	public int getExamRequestId() {
		return examRequestId;
	}

	public void setExamRequestId(int id) {
		this.examRequestId = id;
	}

	public ClientJPA getClient() {
		return client;
	}

	public void setClient(ClientJPA client) {
		this.client = client;
	}

	public List<ExamTestJPA> getExamTestList() {
		return examTestList;
	}

	public void setExamTestList(List<ExamTestJPA> examTestList) {
		this.examTestList = examTestList;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}

@Repository
class ExamRequestRepositoryImpl implements ExamRequestRepository {

    @Autowired
    private ExamRequestJPARepository examRequestJPARepository;

    @Autowired
    private JPAMapper mapper;

    @Override
    public void save(ExamRequest examRequest) {
        ExamRequestJPA examRequestJPA = mapper.map(examRequest, ExamRequestJPA.class);
        examRequestJPARepository.save(examRequestJPA);
    }

    @Override
    public void delete(ExamRequestId id) {
        examRequestJPARepository.deleteById(id.getId());
    }
    
    @Transactional
    @Override
    public ExamRequest get(ExamRequestId id) {
        ExamRequestJPA examRequestJPA = examRequestJPARepository.findById(id.getId()).orElse(null);
        return mapper.map(examRequestJPA, ExamRequest.class);
    }


    @Override
    public void update(ExamRequest examRequest) {
        ExamRequestJPA examRequestJPA = mapper.map(examRequest, ExamRequestJPA.class);
        examRequestJPARepository.save(examRequestJPA);
    }
}


