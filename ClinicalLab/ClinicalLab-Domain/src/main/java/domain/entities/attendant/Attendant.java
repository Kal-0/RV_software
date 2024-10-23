package domain.entities.attendant;

import java.time.LocalDate;

import domain.entities.person.Cpf;
import domain.entities.person.Email;
import domain.entities.person.Person;
import domain.entities.person.PersonId;

public class Attendant extends Person {
	private AttendantId attendantId;
    private String password;
    
	public Attendant(PersonId id, Cpf cpf, Email contactEmail, String name, LocalDate birthDate,
			AttendantId attendantId, String password) {
		super(id, cpf, contactEmail, name, birthDate);
		this.attendantId = attendantId;
		this.password = password;
	}
	
	public Attendant(int id, String cpf, String contactEmail, String name, String birthDate, int attendantId,
			String password) {
		super(id, cpf, contactEmail, name, birthDate);
		this.attendantId = new AttendantId(attendantId);
		this.password = password;
	}




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