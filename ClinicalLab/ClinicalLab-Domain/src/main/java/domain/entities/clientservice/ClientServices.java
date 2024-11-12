package domain.entities.clientservice;

import domain.entities.examrequest.ExamRequestId;
import domain.entities.servicenumber.ServiceNumber;

public class ClientServices {

	private ClientServiceId clientServiceId;
    private ServiceNumber serviceNumber;
    private ExamRequestId examRequestId;
    private String status;
    
	public ClientServices(ClientServiceId clientServiceId, ServiceNumber serviceNumberId,
			ExamRequestId examRequestId, String status) {
		super();
		this.clientServiceId = clientServiceId;
		this.serviceNumber = serviceNumberId;
		this.examRequestId = examRequestId;
		this.status = status;
	}
	
	public ClientServices(ClientServiceId clientServiceId, ServiceNumber serviceNumberId) {
		super();
		this.clientServiceId = clientServiceId;
		this.serviceNumber = serviceNumberId;
		this.status = "waiting for service";
	}
	
	public ClientServices(int clientServiceId, ServiceNumber serviceNumberId) {
		super();
		this.clientServiceId = new ClientServiceId(clientServiceId);
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
		return clientServiceId;
	}
	
	public ExamRequestId getExamRequestId() {
		return examRequestId;
	}

	public void setExamRequestId(ExamRequestId examRequestId) {
		this.examRequestId = examRequestId;
	}
    
    
	
}
