package infrastructure.decoy;

import infrastructure.persistence.memory.MemoryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examtest.ExamTestId;

public class DecoyPersistence2 {
    public static void main(String[] args) {
        
    	MemoryRepository bd = new MemoryRepository();
    	
    	Client c1 = new Client(1, "123.456.789-00", "email@email.com", "cao", "2004-06-14", 1);
    	bd.save(c1);
        
        Client c2 = bd.get(new ClientId(1));
        System.out.println(c2.getName());
//        if (c1_2 != null) {
//            System.out.println("Exam Request salvo com sucesso!");
//            System.out.println(examRequest.getExamRequestId().getId());
//        } else {
//            System.out.println("Falha ao salvar o Exam Request.");
//        }
    }
}
