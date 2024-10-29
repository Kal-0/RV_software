package infrastructure.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clients_Service")
public class ClientServiceJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientServiceId;
	
	@OneToOne
	@JoinColumn(name = "Service_Number_Id")
    private ServiceNumberJPA serviceNumber;
	
	@OneToOne
	@JoinColumn(name = "Exam_Request_Id")
    private ExamRequestJPA examRequest;
    private String status;
}
