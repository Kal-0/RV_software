package domain.common.entities.clientservice;

import domain.common.entities.examrequest.ExamRequestId;

public class ClientService {

	private ClientServiceId id;
    private ServiceNumber serviceNumber;
    private ExamRequestId examRequestId;
    private BloodDrawStatus bloodDrawStatus;
    private String status;
    
	public ClientService(ClientServiceId id, ServiceNumber serviceNumberId,
			ExamRequestId examRequestId, String status) {
		super();
		this.id = id;
		this.serviceNumber = serviceNumberId;
		this.examRequestId = examRequestId;
		this.status = status;
	}
	
	public ClientService(int id, ServiceNumber serviceNumberId,
			ExamRequestId examRequestId) {
		super();
		this.id = new ClientServiceId(id);
		this.serviceNumber = serviceNumberId;
		this.examRequestId = examRequestId;
		this.status = "waiting for service";
	}
	
	public ClientService(int id, ServiceNumber serviceNumberId) {
		super();
		this.id = new ClientServiceId(id);
		this.serviceNumber = serviceNumberId;
		this.status = "waiting for service";
	}

	public ServiceNumber getServiceNumber() {
		return serviceNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ClientServiceId getId() {
		return id;
	}
	
	public ExamRequestId getExamRequestId() {
		return examRequestId;
	}

	public void setExamRequestId(ExamRequestId examRequestId) {
		this.examRequestId = examRequestId;
	}

	public BloodDrawStatus getBloodDrawStatus() {
		return bloodDrawStatus;
	}

	public void setBloodDrawStatus(BloodDrawStatus bloodDrawStatus) {
		this.bloodDrawStatus = bloodDrawStatus;
	}
    
    
	
}
