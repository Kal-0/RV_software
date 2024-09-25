package domain.common.entities.attendant;

import domain.common.entities.person.Person;

public class Attendant extends Person {
    private AttendantId attendantId;
    private String password;
    
    
	public AttendantId getAttendantId() {
		return attendantId;
	}
	public void setAttendantId(AttendantId id) {
		this.attendantId = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

   
}