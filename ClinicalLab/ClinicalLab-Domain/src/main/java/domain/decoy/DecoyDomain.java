package domain.decoy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.common.entities.attendant.Attendant;
import domain.common.entities.client.Client;
import domain.common.entities.clientservice.ClientService;
import domain.common.entities.clientservice.ClientServiceId;
import domain.common.entities.clientservice.ClinicSystem;
import domain.common.entities.clientservice.ServiceNumber;
import domain.common.entities.exam.Exam;
import domain.common.entities.exam.ExamId;
import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examrequest.ExamRequestId;
import domain.common.entities.examtest.ExamTest;
import domain.common.entities.examtest.ExamTestId;
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
		
//		criando exame
		Exam ex1 = new Exam(new ExamId(1), "exame1", "requirements1", 15.00, 2);
		
		ExamTest ext1 = new ExamTest(new ExamTestId(1), ex1.getId(), null);
		List<ExamTestId> lext1 = new ArrayList<ExamTestId>();
		lext1.add(ext1.getId());
		
		ExamRequest er2 = new ExamRequest(new ExamRequestId(1), c1.getClientId(), lext1, LocalDate.now(), null, null, "new");
		ClientService cs1 = new ClientService(1, new ServiceNumber("001", false));
		
		
        
	}

}
