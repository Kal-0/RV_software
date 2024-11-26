package infrastructure.decoy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entities.attendant.Attendant;
import domain.entities.attendant.AttendantRepository;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.client.ClientRepository;
import domain.entities.clientservice.ClientServiceId;
import domain.entities.clientservice.ClientServiceRepository;
import domain.entities.clientservice.ClientServices;
import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examrequest.ExamRequestRepository;
import domain.entities.examtest.ExamTest;
import domain.entities.examtest.ExamTestId;
import domain.entities.examtest.ExamTestRepository;
import domain.entities.servicenumber.ServiceNumber;
import domain.entities.servicenumber.ServiceNumberId;
import domain.entities.servicenumber.ServiceNumberRepository;
import domain.entities.testresult.TestResult;
import domain.entities.testresult.TestResultId;
import domain.entities.testresult.TestResultRepository;

@Component
public class DecoyPersistence4 {

	@Autowired
    private ExamRepository examRepository;
	
	@Autowired
	private ServiceNumberRepository serviceNumberRepository;
	
	@Autowired
	private TestResultRepository testResultRepository;
	
	@Autowired
	private  ExamTestRepository  examTestRepository;
	
	@Autowired
	private ExamRequestRepository examRequestRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AttendantRepository attendantRepository;
	
	@Autowired
	private ClientServiceRepository clientServiceRepository;

    public void executeTest() {
    	
//       CLIENT
    	
        // criacao de cliente
    	Client c1 = new Client(1, "123.456.789-00", "email@email.com", "cao", "2004-06-14", 1);
  		clientRepository.save(c1);
  		
  		Client c1Copy = clientRepository.get(c1.getClientId());
  		System.out.println(c1Copy.getCpf().getCpf());
    	
  		
  		
//  		ATTENDANT
  		//criacao de attendant
  		Attendant a1 = new Attendant(1, "123.456.789-00", "email@email.com", "cao", "2004-06-14", 1, "pass");
  		attendantRepository.save(a1);
  		
  		Attendant a1Copy = attendantRepository.get(a1.getAttendantId());
  		System.out.println(a1Copy.getName());
  		
  		
//  		EXAM
    	
        // Criando instâncias para o Exam
        ExamId examId = new ExamId(1);
        String examName = "Exame de Sangue";
        String requirements = "Nenhum";
        Double price = 150.0;
        int analysisTime = 24;

        // Teste de criação e salvamento de um exame
        Exam newExam = new Exam(examId, examName, requirements, price, analysisTime);
        examRepository.save(newExam);
        System.out.println("Exame salvo: " + newExam);

        // Teste de recuperação do exame
        Exam retrievedExam = examRepository.get(examId);
        if (retrievedExam != null) {
            System.out.println("Exame recuperado: " + retrievedExam);
        } else {
            System.err.println("Erro: Exame não encontrado.");
        }

        // Teste de atualização do exame
        newExam.setName("Exame de Sangue Atualizado");
        examRepository.update(newExam);
        Exam updatedExam = examRepository.get(examId);
        System.out.println("Exame atualizado: " + (updatedExam != null ? updatedExam : "Erro na atualização"));

//        // Teste de deleção do exame
//        examRepository.delete(examId);
//        System.out.println("Exame deletado: " + (examRepository.get(examId) == null ? "Sim" : "Não"));
        
        
//        TEST RESULT
        
        TestResultId testResultId = new TestResultId(1); // Supondo que TestResultId tenha um construtor que aceita um int
        LocalDate resultDate = LocalDate.of(2024, 1, 1);
        String resultContent = "Conteúdo do Resultado de Teste";

        // Teste de criação e salvamento de um TestResult
        TestResult newTestResult = new TestResult(testResultId, resultDate, resultContent);
        testResultRepository.save(newTestResult);
        System.out.println("TestResult salvo: " + newTestResult);

        // Teste de recuperação do TestResult
        TestResult retrievedTestResult = testResultRepository.get(testResultId);
        if (retrievedTestResult != null) {
            System.out.println("TestResult recuperado: " + retrievedTestResult);
        } else {
            System.err.println("Erro: TestResult não encontrado.");
        }

        // Teste de atualização do TestResult
        newTestResult.setResultContent("Conteúdo Atualizado do Resultado de Teste");
        testResultRepository.update(newTestResult);
        TestResult updatedTestResult = testResultRepository.get(testResultId);
        System.out.println("TestResult atualizado: " + (updatedTestResult != null ? updatedTestResult : "Erro na atualização"));

        // Teste de deleção do TestResult (opcional)
        // testResultRepository.delete(testResultId);
        // System.out.println("TestResult deletado: " + (testResultRepository.get(testResultId) == null ? "Sim" : "Não"));
        
        
        
        
//      EXAM TEST  
        
        ExamTestId examTestId = new ExamTestId(1); // Criando um ID para o Teste de Exame
        ExamId examIds = new ExamId(1);
        TestResultId testResultIds = new TestResultId(1);
        String statusExamTest = "Ta testando ainda";
        
        // Teste de criação e salvamento de um ExamTest
        ExamTest newExamTest = new ExamTest(examTestId, examIds, testResultIds, statusExamTest);
        examTestRepository.save(newExamTest); // Salvando o Exame no repositório
        System.out.println("ExamTest salvo: " + newExamTest);
        
        
//      
        
      
      
//    TESTE DO EXAM REQUEST
      
//      // Criando instâncias para o ExamRequest
//      ExamRequestId examRequestId = new ExamRequestId(1);
//      ClientId clientId = new ClientId(1);
//      LocalDate requestDate = LocalDate.of(2024, 1, 15);
//      Double totalPrice = 500.0;
//      String paymentMethod = "Cartão de Crédito";
//      String statUus = "Pendente";
//
//      List<ExamTestId> examTestList = java.util.stream.Stream.of(1)
//      	    .map(ExamTestId::new) // Cria uma instância de ExamTestId para cada número
//      	    .collect(Collectors.toList());
//
//      // Teste de criação e salvamento de um ExamRequest
//      ExamRequest newExamRequest = new ExamRequest(
//          examRequestId,
//          clientId,
//          examTestList,
//          requestDate,
//          totalPrice,
//          paymentMethod,
//          statUus
//      );
//      examRequestRepository.save(newExamRequest);
//      System.out.println("ExamRequest salvo: " + newExamRequest);
//
//      // Teste de recuperação do ExamRequest
//      ExamRequest retrievedExamRequest = examRequestRepository.get(examRequestId);
//      if (retrievedExamRequest != null) {
//          System.out.println("ExamRequest recuperado: " + retrievedExamRequest);
//      } else {
//          System.err.println("Erro: ExamRequest não encontrado.");
//      }
//
//      // Teste de atualização do ExamRequest
//      newExamRequest.setStatus("Finalizado");
//      examRequestRepository.update(newExamRequest);
//      ExamRequest updatedExamRequest = examRequestRepository.get(examRequestId);
//      System.out.println("ExamRequest atualizado: " + (updatedExamRequest != null ? updatedExamRequest : "Erro na atualização"));
//
//      // Teste de deleção do ExamRequest (opcional)
//      // examRequestRepository.delete(examRequestId);
//      // System.out.println("ExamRequest deletado: " + (examRequestRepository.get(examRequestId) == null ? "Sim" : "Não"));
        
      
      
        
        
        
//        SERVICE NUMBER

        // Criando instâncias para o ServiceNumber
        ServiceNumberId serviceNumberId = new ServiceNumberId(1); // Supondo que ServiceNumberId tenha um construtor que aceita um int
        String number = "SN12345";
        boolean isPriority = true;
        String status = "Ativo";

        // Teste de criação e salvamento de um ServiceNumber
        ServiceNumber newServiceNumber = new ServiceNumber(serviceNumberId, number, isPriority, status);
        serviceNumberRepository.save(newServiceNumber);
        System.out.println("ServiceNumber salvo: " + newServiceNumber);

        // Teste de recuperação do ServiceNumber
        ServiceNumber retrievedServiceNumber = serviceNumberRepository.get(serviceNumberId);
        if (retrievedServiceNumber != null) {
            System.out.println("ServiceNumber recuperado: " + retrievedServiceNumber);
        } else {
            System.err.println("Erro: ServiceNumber não encontrado.");
        }

        // Teste de atualização do ServiceNumber
        newServiceNumber.setStatus("Inativo");
        serviceNumberRepository.update(newServiceNumber);
        ServiceNumber updatedServiceNumber = serviceNumberRepository.get(serviceNumberId);
        System.out.println("ServiceNumber atualizado: " + (updatedServiceNumber != null ? updatedServiceNumber : "Erro na atualização"));

//        // Teste de deleção do ServiceNumber
//        serviceNumberRepository.delete(serviceNumberId);
//        System.out.println("ServiceNumber deletado: " + (serviceNumberRepository.get(serviceNumberId) == null ? "Sim" : "Não"));
        
        
        
        


        

        
     // CLIENT SERVICES
        System.out.println("== TESTANDO CLIENT SERVICES ==");

        // Criando um ServiceNumber para associar ao ClientServices
        ServiceNumberId serviceNumberIdForClientService = new ServiceNumberId(2); // ID para o ServiceNumber
        String serviceNumberString = "SN54321";
        boolean serviceNumberPriority = false;
        String serviceNumberStatus = "Pendente";

        ServiceNumber serviceNumberForClientService = new ServiceNumber(
            serviceNumberIdForClientService,
            serviceNumberString,
            serviceNumberPriority,
            serviceNumberStatus
        );

        // Salvando o ServiceNumber no repositório
        serviceNumberRepository.save(serviceNumberForClientService);
        System.out.println("ServiceNumber para ClientServices salvo: " + serviceNumberForClientService);

        // Criando um ExamRequest para associar ao ClientServices (opcional)
        ExamRequestId examRequestIdForClientService = new ExamRequestId(1);
        ClientId clientIdForExamRequest = new ClientId(1);
        LocalDate examRequestDate = LocalDate.of(2024, 12, 15);
        Double examRequestTotalPrice = 300.0;
        String examRequestPaymentMethod = "Boleto";
        String examRequestStatus = "Aguardando Pagamento";

        ExamRequest examRequestForClientService = new ExamRequest(
            examRequestIdForClientService,
            clientIdForExamRequest,
            Arrays.asList(new ExamTestId(1)), // Lista de testes de exame
            examRequestDate,
            examRequestTotalPrice,
            examRequestPaymentMethod,
            examRequestStatus
        );

        // Salvando o ExamRequest no repositório
        examRequestRepository.save(examRequestForClientService);
        System.out.println("ExamRequest para ClientServices salvo: " + examRequestForClientService);

        // Criando um ClientServices com os objetos associados
        ClientServiceId clientServiceId = new ClientServiceId(1);
        String clientServiceStatus = "Em Progresso";

        ClientServices clientServices = new ClientServices(
            clientServiceId,
            serviceNumberForClientService,
            examRequestIdForClientService,
            clientServiceStatus
        );

        // Salvando o ClientServices no repositório
        clientServiceRepository.save(clientServices);
        System.out.println("ClientServices salvo: " + clientServices);

        // Recuperando o ClientServices
        ClientServices retrievedClientServices = clientServiceRepository.get(clientServiceId);
        if (retrievedClientServices != null) {
            System.out.println("ClientServices recuperado: " + retrievedClientServices);
        } else {
            System.err.println("Erro: ClientServices não encontrado.");
        }

        // Atualizando o ClientServices
        retrievedClientServices.setStatus("Finalizado");
        clientServiceRepository.update(retrievedClientServices);
        ClientServices updatedClientServices = clientServiceRepository.get(clientServiceId);
        System.out.println("ClientServices atualizado: " + (updatedClientServices != null ? updatedClientServices : "Erro na atualização"));

        // Opcional: Teste de deleção
        // clientServiceRepository.delete(clientServiceId);
        // System.out.println("ClientServices deletado: " + (clientServiceRepository.get(clientServiceId) == null ? "Sim" : "Não"));


    }
}
