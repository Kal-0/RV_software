package domain.common.entities.examrequest;

import java.util.Date;
import java.util.List;

import domain.common.entities.examtest.ExamTestId;
import domain.common.entities.client.ClientId;

public class ExamRequest {
    private ExamRequestId id;
    private ClientId clientId;
    private List<ExamTestId> examTestList;
    private Date requestDate;
    private Double totalPrice;
    private String paymentMethod;
    private String status;
    
	public ExamRequest(ExamRequestId id, ClientId clientId, List<ExamTestId> examTestList, Date requestDate,
			Double totalPrice, String paymentMethod, String status) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.examTestList = examTestList;
		this.requestDate = requestDate;
		this.totalPrice = totalPrice;
		this.paymentMethod = paymentMethod;
		this.status = status;
	}

	public List<ExamTestId> getExamTestList() {
		return examTestList;
	}

	public void setExamTestList(List<ExamTestId> examTestList) {
		this.examTestList = examTestList;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
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

	public ExamRequestId getId() {
		return id;
	}

	public ClientId getClientId() {
		return clientId;
	}
    
    
}
