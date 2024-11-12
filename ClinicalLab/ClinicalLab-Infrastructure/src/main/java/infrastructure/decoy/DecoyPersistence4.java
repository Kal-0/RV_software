package infrastructure.decoy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;

@Component
public class DecoyPersistence4 {

    private final ExamRepository examRepository;

    @Autowired
    public DecoyPersistence4(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

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
    }
}
