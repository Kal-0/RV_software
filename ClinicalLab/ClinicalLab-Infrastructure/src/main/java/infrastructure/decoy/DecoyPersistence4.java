package infrastructure.decoy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import domain.entities.servicenumber.ServiceNumber;
import domain.entities.servicenumber.ServiceNumberId;
import domain.entities.servicenumber.ServiceNumberRepository;

@Component
public class DecoyPersistence4 {

	@Autowired
    private ExamRepository examRepository;
	
	@Autowired
	private ServiceNumberRepository serviceNumberRepository;
	

    public void executeTest() {
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
    
    }
}
