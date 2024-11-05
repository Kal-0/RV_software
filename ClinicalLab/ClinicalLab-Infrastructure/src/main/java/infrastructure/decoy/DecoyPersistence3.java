package infrastructure.decoy;

import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.client.ClientRepository;
import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import domain.entities.person.Cpf;
import domain.entities.person.Email;
import domain.entities.person.PersonId;
import infrastructure.Aplicacao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class DecoyPersistence3 {

    public static void main(String[] args) {
        // Criando o contexto do Spring
        ApplicationContext context = new AnnotationConfigApplicationContext(Aplicacao.class);

        // Obtendo a instância do repositório de Client a partir do contexto
        ClientRepository clientRepository = context.getBean(ClientRepository.class);
        
        // Obtendo a instância do repositório de Exam a partir do contexto
        ExamRepository examRepository = context.getBean(ExamRepository.class);
        /*
        // Criando instâncias necessárias para o Client
        PersonId personId = new PersonId(1);
        Cpf cpf = new Cpf("123.456.789-00");
        Email contactEmail = new Email("cliente@exemplo.com");
        String name = "Nome do Cliente";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        ClientId clientId = new ClientId(1);

        // Teste de criação e salvamento de um cliente
        Client newClient = new Client(personId, cpf, contactEmail, name, birthDate, clientId);

        // Salvar o cliente
        clientRepository.save(newClient);
        System.out.println("========================================================");
        System.out.println("Cliente salvo: " + newClient);
        System.out.println("========================================================");

        // Teste de recuperação do cliente
        Client retrievedClient = clientRepository.get(clientId);
        System.out.println("========================================================");
        System.out.println("Cliente recuperado: " + retrievedClient.toString());
        System.out.println("========================================================");

        // Teste de atualização do cliente
        newClient.setName("Nome do Cliente Atualizado");
        clientRepository.update(newClient);
        System.out.println("========================================================");
        System.out.println("Cliente atualizado: " + clientRepository.get(clientId));
        System.out.println("========================================================");
        
        */

        // Teste de deleção do cliente
        //clientRepository.delete(clientId);
        //System.out.println("Cliente deletado: " + (clientRepository.get(clientId) == null ? "Sim" : "Não"));
        
        // Criando instâncias necessárias para o Exam
        ExamId examId = new ExamId(1);
        String examName = "Exame de Sangue";
        String requirements = "Nenhum";
        Double price = 150.0;
        int analysisTime = 24;

        // Teste de criação e salvamento de um exame
        Exam newExam = new Exam(examId, examName, requirements, price, analysisTime);
        
        // Salvar o exame
        examRepository.save(newExam);
        System.out.println("========================================================");
        System.out.println("Exame salvo: " + newExam);
        System.out.println("========================================================");

        
        // Teste de recuperação do exame
        Exam retrievedExam = examRepository.get(examId);
        System.out.println("========================================================");
        System.out.println("Exame recuperado: " + retrievedExam);
        System.out.println("========================================================");

/*
        // Teste de atualização do exame
        newExam.setName("Exame de Sangue Atualizado");
        examRepository.update(newExam);
        System.out.println("========================================================");
        System.out.println("Exame atualizado: " + examRepository.get(examId));
        System.out.println("========================================================");
        */
        
        // Teste de deleção do exame
        //examRepository.delete(examId);
        //System.out.println("Exame deletado: " + (examRepository.get(examId) == null ? "Sim" : "Não"));
    }
}
