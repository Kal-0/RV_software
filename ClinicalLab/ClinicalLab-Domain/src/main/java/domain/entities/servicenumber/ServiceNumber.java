package domain.entities.servicenumber;

public class ServiceNumber {
    private ServiceNumberId id;
    private String number;
    private boolean isPriority;
    private String status;
    
	public ServiceNumber(ServiceNumberId id, String number, boolean isPriority, String status) {
		super();
		this.id = id;
		this.number = number;
		this.isPriority = isPriority;
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public ServiceNumberId getId() {
		return id;
	}

    
}
