package domain.decoy;

import domain.common.entities.attendant.Attendant;
import domain.common.entities.client.Client;
import domain.common.entities.clientservice.ClientService;
import domain.common.entities.clientservice.ClientServiceId;
import domain.common.entities.clientservice.ClinicSystem;
import domain.common.entities.clientservice.ServiceNumber;
import domain.common.entities.person.Cpf;
import domain.common.entities.person.Email;
import domain.common.entities.person.Person;

public class DecoyDomain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Email email1 = new Email("caio@gmail.com");
		System.out.println(email1.getEmailText());
		
		Cpf cpf1 = new Cpf("123456789-00");
		System.out.println(cpf1.getCpf());
		
		Person p1 = new Person(1, "123456789-00", "caioba@tome.com", "caio", "2004-06-14");
		System.out.println(p1.getBirthDate());
		System.out.println(p1.getAge());
		
		Client c1 = new Client(2, "123456789-00", "client1@tome.com", "client1", "2004-06-14", 1);
		System.out.println(c1.getName());
		
		Attendant a1 = new Attendant(3, "123456789-00", "attendant@tome.com", "attendant1", "2004-06-14", 1, "password");
		System.out.println(a1.getName());
		

		ClinicSystem clinicSystem;
		
		clinicSystem = new ClinicSystem();
		
        ClientService client1 = new ClientService(new ClientServiceId(1), new ServiceNumber("001"), null, true, "Aguardando");
        ClientService client2 = new ClientService(new ClientServiceId(2), new ServiceNumber("002"), null, false, "Aguardando");
        ClientService client3 = new ClientService(new ClientServiceId(3), new ServiceNumber("003"), null, true, "Aguardando");
        ClientService client4 = new ClientService(new ClientServiceId(4), new ServiceNumber("004"), null, true, "Aguardando");

        // Adiciona clientes
        clinicSystem.handleClientArrival(client1);
        clinicSystem.handleClientArrival(client2);
        clinicSystem.handleClientArrival(client3);
        clinicSystem.handleClientArrival(client4);
        
        System.out.println(clinicSystem.callNextClient().getServiceNumber().getNumber());
        System.out.println(clinicSystem.callNextClient().getServiceNumber().getNumber());
        System.out.println(clinicSystem.callNextClient().getServiceNumber().getNumber());
        System.out.println(clinicSystem.callNextClient().getServiceNumber().getNumber());
        
	}

}
