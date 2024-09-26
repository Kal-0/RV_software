package domain.common.entities.person;

import java.util.Date;

public class Person {
    private PersonId id;
    private Cpf cpf;
    private Email contactEmail;
    private String name;
    private Date birthDate;
    
    
    
    
	public Person(PersonId id, Cpf cpf, Email contactEmail, String name, Date birthDate) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.contactEmail = contactEmail;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Cpf getCpf() {
		return cpf;
	}
	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}
	public Email getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(Email contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public PersonId getId() {
		return id;
	}

    
}

