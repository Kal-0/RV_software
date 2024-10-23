package domain.entities.person;

public interface PersonRepository {

	public void save(Person person);
	
	public void delete(PersonId id);
	
	public Person get(PersonId id);
	
	public void update(Person person);
	
}
