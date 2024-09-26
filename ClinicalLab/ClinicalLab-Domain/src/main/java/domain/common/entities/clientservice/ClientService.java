package domain.common.entities.clientservice;

import domain.common.entities.examrequest.ExamRequestId;

public class ClientService {

    private ClientServiceId id;
    private ServiceNumber serviceNumber;
    private ExamRequestId examRequestId;
    private boolean isPriority;
    private String status;
    
	public ClientService(ClientServiceId id, ServiceNumber serviceNumber, ExamRequestId examRequestId,
			boolean isPriority, String status) {
		super();
		this.id = id;
		this.serviceNumber = serviceNumber;
		this.examRequestId = examRequestId;
		this.isPriority = isPriority;
		this.status = status;
	}

	public ServiceNumber getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(ServiceNumber serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public boolean isPriority() {
		return isPriority;
	}

	public void setPriority(boolean isPriority) {
		this.isPriority = isPriority;
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
    
    
	
}
