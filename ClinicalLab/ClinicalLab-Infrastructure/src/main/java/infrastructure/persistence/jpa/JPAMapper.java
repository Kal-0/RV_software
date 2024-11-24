package infrastructure.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entities.attendant.Attendant;
import domain.entities.attendant.AttendantId;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examtest.ExamTest;
import domain.entities.examtest.ExamTestId;
import domain.entities.person.Cpf;
import domain.entities.person.Email;
import domain.entities.person.Person;
import domain.entities.person.PersonId;
import domain.entities.servicenumber.ServiceNumber;
import domain.entities.servicenumber.ServiceNumberId;
import domain.entities.testresult.TestResult;
import domain.entities.testresult.TestResultId;

@Component
public class JPAMapper extends ModelMapper {

	
    @Autowired
    public JPAMapper() {
        Configuration configuration = getConfiguration();
        configuration.setFieldMatchingEnabled(true);
        configuration.setFieldAccessLevel(AccessLevel.PRIVATE);

        // Adiciona conversores genéricos aqui
        addGenericConverters();
    }

    private void addGenericConverters() {
    	// PERSON =========================================

    	  // Conversor para PersonJPA -> Person
    	  addConverter(new AbstractConverter<PersonJPA, Person>() {
    	      @Override
    	      protected Person convert(PersonJPA source) {
    	          return new Person(
    	              new PersonId(source.getId()),
    	              new Cpf(source.getCpf()),
    	              new Email(source.getContactEmail()),
    	              source.getName(),
    	              source.getBirthDate()
    	          );
    	      }
    	  });

    	  // Conversor para Person -> PersonJPA
    	  addConverter(new AbstractConverter<Person, PersonJPA>() {
    	      @Override
    	      protected PersonJPA convert(Person source) {
    	          PersonJPA personJPA = new PersonJPA();
    	          personJPA.setId(source.getId().getId());
    	          personJPA.setCpf(source.getCpf().getCpf());
    	          personJPA.setContactEmail(source.getContactEmail().getEmailText());
    	          personJPA.setName(source.getName());
    	          personJPA.setBirthDate(source.getBirthDate());
    	          return personJPA;
    	      }
    	  });
    	
    	
//    	==============CLIENT=================
    	
    	
        // Converter para ClientJPA -> Client
        addConverter(new AbstractConverter<ClientJPA, Client>() {
            @Override
            protected Client convert(ClientJPA source) {
                return new Client(
                    new PersonId(source.getId()),
                    new Cpf(source.getCpf()),
                    new Email(source.getContactEmail()),
                    source.getName(),
                    source.getBirthDate(),
                    new ClientId(source.getClientId())
                );
            }
        });
        
        // Converter para Client -> ClientJPA
        addConverter(new AbstractConverter<Client, ClientJPA>() {
            @Override
            protected ClientJPA convert(Client source) {
                ClientJPA clientJPA = new ClientJPA();
                clientJPA.setId(source.getId().getId());
                clientJPA.setCpf(source.getCpf().getCpf());
                clientJPA.setContactEmail(source.getContactEmail().getEmailText());
                clientJPA.setName(source.getName());
                clientJPA.setBirthDate(source.getBirthDate());
                clientJPA.setClientId(source.getClientId().getId());
                return clientJPA;
            }
        });
        
        
        
//    	==============ATTENDANT=================
    	
    	
        // Converter para AttendantJPA -> Attendant
        addConverter(new AbstractConverter<AttendantJPA, Attendant>() {
            @Override
            protected Attendant convert(AttendantJPA source) {
                return new Attendant(
                    new PersonId(source.getId()),
                    new Cpf(source.getCpf()),
                    new Email(source.getContactEmail()),
                    source.getName(),
                    source.getBirthDate(),
                    new AttendantId(source.getAttendantId()),
                    source.getPassword()
                );
            }
        });
        
     // Converter para Attendant -> AttendantJPA
        addConverter(new AbstractConverter<Attendant, AttendantJPA>() {
            @Override
            protected AttendantJPA convert(Attendant source) {
                AttendantJPA attendantJPA = new AttendantJPA();
                attendantJPA.setId(source.getId().getId());
                attendantJPA.setCpf(source.getCpf().getCpf());
                attendantJPA.setContactEmail(source.getContactEmail().getEmailText());
                attendantJPA.setName(source.getName());
                attendantJPA.setBirthDate(source.getBirthDate());
                attendantJPA.setAttendantId(source.getAttendantId().getId());
                attendantJPA.setPassword(source.getPassword());
                return attendantJPA;
            }
        });

        
        
        
        
        

        
        // EXAM ============================================================
        // Converter para ExamJPA -> Exam
        addConverter(new AbstractConverter<ExamJPA, Exam>() {
            @Override
            protected Exam convert(ExamJPA source) {
                return new Exam(
                    new ExamId(source.getId()),
                    source.getName(),
                    source.getRequirements(),
                    source.getPrice(),
                    source.getAnalysisTime()
                );
            }
        });

        // Converter para Exam -> ExamJPA
        addConverter(new AbstractConverter<Exam, ExamJPA>() {
            @Override
            protected ExamJPA convert(Exam source) {
                ExamJPA examJPA = new ExamJPA();
                examJPA.setId(source.getId().getId());  
                examJPA.setName(source.getName());
                examJPA.setRequirements(source.getRequirements());
                examJPA.setPrice(source.getPrice());
                examJPA.setAnalysisTime(source.getAnalysisTime());
                return examJPA;
            }
        });
        
//        SERVICENUMBER======================================
     // Converter para ServiceNumberJPA -> ServiceNumber
        addConverter(new AbstractConverter<ServiceNumberJPA, ServiceNumber>() {
            @Override
            protected ServiceNumber convert(ServiceNumberJPA source) {
                return new ServiceNumber(
                    new ServiceNumberId(source.id),
                    source.number,
                    source.isPriority,
                    source.status
                );
            }
        });

        // Converter para ServiceNumber -> ServiceNumberJPA
        addConverter(new AbstractConverter<ServiceNumber, ServiceNumberJPA>() {
            @Override
            protected ServiceNumberJPA convert(ServiceNumber source) {
                ServiceNumberJPA ServiceNumberJPA = new ServiceNumberJPA();
                ServiceNumberJPA.id = source.getId().getId();  
                ServiceNumberJPA.number = source.getNumber();
                ServiceNumberJPA.isPriority = source.isPriority();
                ServiceNumberJPA.status = source.getStatus();
                return ServiceNumberJPA;
            }
        });
        
        // TEST RESULT ==================================
        
     // Converter para TestResultJPA -> TestResult
        addConverter(new AbstractConverter<TestResultJPA, TestResult>() {
            @Override
            protected TestResult convert(TestResultJPA source) {
                return new TestResult(
                    new TestResultId(source.getTestResultId()),
                    source.getResultDate(),
                    source.getResultContent()
                );
            }
        });

        // Converter para TestResult -> TestResultJPA
        addConverter(new AbstractConverter<TestResult, TestResultJPA>() {
            @Override
            protected TestResultJPA convert(TestResult source) {
                TestResultJPA testResultJPA = new TestResultJPA();
                testResultJPA.setTestResultId(source.getId().getId());
                testResultJPA.setResultDate(source.getResultDate());
                testResultJPA.setResultContent(source.getResultContent());
                return testResultJPA;
            }
        });
        
        
     // EXAM REQUEST ==================================
        
        // Conversor para ExamRequestJPA -> ExamRequest
        addConverter(new AbstractConverter<ExamRequestJPA, ExamRequest>() {
        	@Override
        	protected ExamRequest convert(ExamRequestJPA source) {
             return new ExamRequest(
                 new ExamRequestId(source.getExamRequestId()),
                 new ClientId(source.getClient().getId()),  // Supondo que ClientJPA tenha um método getId()
                 source.getExamTestList().stream()
                       .map(examTestJPA -> new ExamTestId(examTestJPA.getExamTestId()))  // Supondo que ExamTestJPA tenha um método getExamTestId()
                       .collect(Collectors.toList()),
                 source.getRequestDate(),
                 source.getTotalPrice(),
                 source.getPaymentMethod(),
                 source.getStatus()
             );
         }
     });

     // Conversor para ExamRequest -> ExamRequestJPA
     addConverter(new AbstractConverter<ExamRequest, ExamRequestJPA>() {
         @Override
         protected ExamRequestJPA convert(ExamRequest source) {
             ExamRequestJPA examRequestJPA = new ExamRequestJPA();
             examRequestJPA.setExamRequestId(source.getExamRequestId().getId());
             
             // Obtém o ClientJPA usando o ClientId
             ClientJPA client = map(source.getClientId(), ClientJPA.class);
             examRequestJPA.setClient(client);

             // Converte a lista de ExamTestId para uma lista de ExamTestJPA
             List<ExamTestJPA> examTestJPAList = source.getExamTestList().stream()
                 .map(examTestId -> map(examTestId, ExamTestJPA.class))
                 .collect(Collectors.toList());
             examRequestJPA.setExamTestList(examTestJPAList);

             examRequestJPA.setRequestDate(source.getRequestDate());
             examRequestJPA.setTotalPrice(source.getTotalPrice());
             examRequestJPA.setPaymentMethod(source.getPaymentMethod());
             examRequestJPA.setStatus(source.getStatus());

             	return examRequestJPA;
         	}
     	});



        
	     // EXAM TEST ============================
	
	     // Converter de ExamTestJPA -> ExamTest
	     addConverter(new AbstractConverter<ExamTestJPA, ExamTest>() {
	         @Override
	         protected ExamTest convert(ExamTestJPA source) {
	             return new ExamTest(
	                 new ExamTestId(source.getExamTestId()),
	                 new ExamId(source.getExam().getId()),  // Obtém o ID do Exam
	                 new TestResultId(source.getTestResult().getTestResultId()),
	                 source.getStatus()
	             );
	         }
	     });
	
	     // Converter de ExamTest -> ExamTestJPA
	     addConverter(new AbstractConverter<ExamTest, ExamTestJPA>() {
	         @Override
	         protected ExamTestJPA convert(ExamTest source) {
	             ExamTestJPA examTestJPA = new ExamTestJPA();
	             examTestJPA.setExamTestId(source.getId().getId());
	             
	             // Mapeamento do objeto Exam e TestResult
	             examTestJPA.setExam(map(source.getExamId(), ExamJPA.class));
	             examTestJPA.setTestResult(map(source.getTestResultId(), TestResultJPA.class));
	             
             examTestJPA.setStatus(source.getStatus());
             return examTestJPA;
         }
     });
	     
	 

        
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return source != null ? super.map(source, destinationType) : null;
    }
}
