package infrastructure.persistence.jpa;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entities.person.Person;
import domain.entities.person.PersonId;
import domain.entities.person.PersonRepository;
import infrastructure.persistence.jpa.repository.PersonJPARepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "People")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cpf;
    private String contactEmail;
    private String name;
    private LocalDate birthDate;
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
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

    // Construtores, getters e setters
    
}
@Repository
class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private PersonJPARepository personJPARepository;

    @Autowired
    private JPAMapper mapper;

    @Override
    public void save(Person person) {
        PersonJPA personJPA = mapper.map(person, PersonJPA.class);
        personJPARepository.save(personJPA);
    }

    @Override
    public void delete(PersonId id) {
        personJPARepository.deleteById(id.getId());
    }

    @Override
    public Person get(PersonId id) {
        PersonJPA personJPA = personJPARepository.findById(id.getId()).orElse(null);
        return mapper.map(personJPA, Person.class);
    }

    @Override
    public void update(Person person) {
        PersonJPA personJPA = mapper.map(person, PersonJPA.class);
        personJPARepository.save(personJPA);
    }
}