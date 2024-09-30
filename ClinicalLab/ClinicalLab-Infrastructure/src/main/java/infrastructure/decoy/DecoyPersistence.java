package infrastructure.decoy;

import infrastructure.persistence.memory.MemoryRepository;
import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examrequest.ExamRequestId;
import domain.common.entities.client.ClientId;
import domain.common.entities.examtest.ExamTestId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DecoyPersistence {

    public static void main(String[] args) {
        // Criando um ExamRequestId e ClientId fictícios com ID específico
        ExamRequestId examRequestId = new ExamRequestId(1); // Usando o construtor com ID
        ClientId clientId = new ClientId(1001); // Substitua com um ID fictício

        // Criando uma lista fictícia de ExamTestId
        List<ExamTestId> examTestList = new ArrayList<>();
        examTestList.add(new ExamTestId(101)); // Adicionando um identificador fictício para os testes de exame

        // Definindo a data da solicitação como a data atual
        LocalDate requestDate = LocalDate.now();

        // Definindo um preço total fictício
        Double totalPrice = 200.0;

        // Definindo o método de pagamento fictício
        String paymentMethod = "Cartão de Crédito";

        // Definindo o status da solicitação como "Pendente"
        String status = "Pendente";

        // Criando uma instância de ExamRequest com os dados fictícios
        ExamRequest examRequest = new ExamRequest(examRequestId, clientId, examTestList, requestDate, totalPrice, paymentMethod, status);

        // Criando a instância de MemoryRepository
        MemoryRepository memoryRepository = new MemoryRepository();

        // Salvando o objeto ExamRequest no repositório
        memoryRepository.save(examRequest);

        // Verificando se o exame foi salvo corretamente
        ExamRequest savedExamRequest = memoryRepository.get(examRequestId);

        if (savedExamRequest != null) {
            System.out.println("Exam Request salvo com sucesso!");
        } else {
            System.out.println("Falha ao salvar o Exam Request.");
        }
    }
}
