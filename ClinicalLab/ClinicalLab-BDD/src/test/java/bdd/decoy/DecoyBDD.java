package bdd.decoy;

import domain.entities.attendant.Attendant;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.person.Cpf;
import domain.entities.person.Email;
import domain.entities.person.Person;
import infrastructure.persistence.memory.MemoryRepository;

public class DecoyBDD {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MemoryRepository repo = new MemoryRepository();
		
		Email email1 = new Email("caio@gmail.com");
		System.out.println(email1.getEmailText());
		
		Cpf cpf1 = new Cpf("123456789-00");
		System.out.println(cpf1.getCpf());
		
		Person p1 = new Person(1, "123456789-00", "caioba@tome.com", "caio", "2004-06-14");
		System.out.println(p1.getBirthDate());
		System.out.println(p1.getAge());
		
		Client c1 = new Client(2, "123456789-00", "client1@tome.com", "client1", "2004-06-14", 1);
		Client c2 = new Client(4, "123456789-00", "client2@tome.com", "client2", "2004-06-14", 2);
		System.out.println(c1.getName());
		
		Attendant a1 = new Attendant(3, "123456789-00", "attendant@tome.com", "attendant1", "2004-06-14", 1, "password");
		System.out.println(a1.getName());
		
		repo.save(c1);
		repo.save(c2);
		
		System.out.println(repo.get(c1.getClientId()).getName());
		System.out.println(repo.get(new ClientId(2)).getName());
		
	}
}
