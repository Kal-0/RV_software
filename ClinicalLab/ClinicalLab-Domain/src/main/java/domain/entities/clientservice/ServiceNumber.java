package domain.entities.clientservice;

public class ServiceNumber {
	
	private String number;
	private boolean priority;
	

	public ServiceNumber(String number, boolean priority) {
		super();
		this.number = number;
		this.priority = priority;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}
	
	
	
	
}
