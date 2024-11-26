package infrastructure.domainevent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ExamTestEventListener {

    @EventListener
    public void handleExamTestSavedEvent(ExamTestSavedEvent event) {
        System.out.println("Log: ExamTest salvo. ID: " + event.getExamTest().getId() +
                           ", Status: " + event.getExamTest().getStatus());
        // Aqui você pode escrever em um arquivo ou sistema de log externo
    }
}

