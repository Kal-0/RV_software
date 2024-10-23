package domain.entities.person;

import java.time.LocalDate;
import java.time.Period;


public class Person {
    private PersonId id;
    private Cpf cpf;
    private Email contactEmail;
    private String name;
    private LocalDate birthDate;
    
    
	public Person(PersonId id, Cpf cpf, Email contactEmail, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.contactEmail = contactEmail;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Person(int id, String cpf, String contactEmail, String name, String birthDate) {
		super();
		this.id = new PersonId(id);
		this.cpf = new Cpf(cpf);
		this.contactEmail = new Email(contactEmail);
		this.name = name;
		this.birthDate = LocalDate.parse(birthDate) ;
	}
	
	
	public PersonId getId() {
		return id;
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
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public int getAge() {
		int age = Period.between(birthDate, LocalDate.now()).getYears();
		return age;
	}

    
}

