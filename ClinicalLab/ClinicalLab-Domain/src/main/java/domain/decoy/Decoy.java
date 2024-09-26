package domain.decoy;

import domain.common.entities.attendant.Attendant;
import domain.common.entities.client.Client;
import domain.common.entities.person.Cpf;
import domain.common.entities.person.Email;
import domain.common.entities.person.Person;

public class Decoy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Email email1 = new Email("caio@gmail.com");
		System.out.println(email1.getEmailText());
		
		Cpf cpf1 = new Cpf("123456789-00");
		System.out.println(cpf1.getCpf());
		
		Person p1 = new Person(1, "123456789-00", "caioba@tome.com", "caio", "2004-06-14");
		System.out.println(p1.getBirthDate());
		
		Client c1 = new Client(2, "123456789-00", "client1@tome.com", "client1", "2004-06-14", 1);
		System.out.println(c1.getName());
		
		Attendant a1 = new Attendant(3, "123456789-00", "attendant@tome.com", "attendant1", "2004-06-14", 1, "password");
		System.out.println(a1.getName());
	}

}
