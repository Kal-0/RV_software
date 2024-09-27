package domain.common.entities.clientservice;

import domain.common.entities.examrequest.ExamRequestId;

public class ClientService {

	private ClientServiceId id;
    private ServiceNumberId serviceNumberId;
    private ExamRequestId examRequestId;
    private BloodDrawStatus bloodDrawStatus;
    private String status;
    
	public ClientService(ClientServiceId id, ServiceNumberId serviceNumberId,
			ExamRequestId examRequestId, String status) {
		super();
		this.id = id;
		this.serviceNumberId = serviceNumberId;
		this.examRequestId = examRequestId;
		this.status = status;
	}

	public ServiceNumberId getServiceNumber() {
		return serviceNumberId;
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

	public BloodDrawStatus getBloodDrawStatus() {
		return bloodDrawStatus;
	}

	public void setBloodDrawStatus(BloodDrawStatus bloodDrawStatus) {
		this.bloodDrawStatus = bloodDrawStatus;
	}
    
    
	
}
