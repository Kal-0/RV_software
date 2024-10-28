package domain.entities.examrequest;

import java.time.LocalDate;
import java.util.List;


import domain.entities.client.ClientId;
import domain.entities.examtest.ExamTestId;

public class ExamRequest {
	
	    
	private ExamRequestId examRequestId;
	private ClientId clientId;
	private List<ExamTestId> examTestList;
	private LocalDate requestDate;
	private Double totalPrice;
	private String paymentMethod;
	private String status;

    

    public ExamRequest(ExamRequestId examRequestId, ClientId clientId, List<ExamTestId> examTestList, LocalDate requestDate,
                       Double totalPrice, String paymentMethod, String status) {
        this.examRequestId = examRequestId;
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

	public ExamRequestId getExamRequestId() {
		return examRequestId;
	}
	
	public void setExamRequestId(ExamRequestId examRequestId) {
		 this.examRequestId = examRequestId;
	}

	public ClientId getClientId() {
		return clientId;
	}
	
	public void setClientId(ClientId clientId) {
		this.clientId = clientId;
	}
	
	
	public String getStatus() {
	     return status;
	}

	public void setStatus(String status) {
	     this.status = status;
	}
	
	public boolean isTotalPriceCalculated() {
	    return this.totalPrice != null && this.totalPrice > 0;
	}
	
    
}
