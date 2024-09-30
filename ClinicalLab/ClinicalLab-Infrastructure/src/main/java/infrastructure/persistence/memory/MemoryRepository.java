package infrastructure.persistence.memory;

import java.util.HashMap;
import java.util.Map;

import domain.common.entities.attendant.Attendant;
import domain.common.entities.attendant.AttendantId;
import domain.common.entities.attendant.AttendantRepository;
import domain.common.entities.client.Client;
import domain.common.entities.client.ClientId;
import domain.common.entities.client.ClientRepository;
import domain.common.entities.exam.Exam;
import domain.common.entities.exam.ExamId;
import domain.common.entities.exam.ExamRepository;


/*implement the repositories here*/
public class MemoryRepository implements ExamRepository, ClientRepository, AttendantRepository {
	private Map<ExamId, Exam> exams = new HashMap<>();
	private Map<ClientId, Client> clients = new HashMap<>();
    private Map<AttendantId, Attendant> attendants = new HashMap<>();

//    EXAMINATION
    /* Exam */
    @Override
    public void save(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("The exam can not be null");
        }
        exams.put(exam.getId(), exam);
    }

    @Override
    public void delete(ExamId examId) {
        if (examId == null) {
            throw new IllegalArgumentException("The exam ID can not be null");
        }
        exams.remove(examId);
    }

    @Override
    public Exam get(ExamId examId) {
        if (examId == null) {
            throw new IllegalArgumentException("The exam ID can not be null");
        }
        return exams.get(examId); // Retorna o exame ou null se não existir
    }

    @Override
    public void update(Exam exam) {
        if (exam == null) {
            throw new IllegalArgumentException("The exam can not be null");
        }
        exams.put(exam.getId(), exam);
    }
    
    
    
//    PERSON
    /* Client Methods */
    @Override
    public void save(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client can not be null");
        }
        clients.put(client.getClientId(), client);
    }

    @Override
    public void delete(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("The client ID can not be null");
        }
        clients.remove(clientId);
    }

    @Override
    public Client get(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("The client ID can not be null");
        }
        return clients.get(clientId); // Retorna o cliente ou null se não existir
    }

    @Override
    public void update(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client can not be null");
        }
        clients.put(client.getClientId(), client);
    }

    /* Attendant Methods */
    @Override
    public void save(Attendant attendant) {
        if (attendant == null) {
            throw new IllegalArgumentException("The attendant can not be null");
        }
        attendants.put(attendant.getAttendantId(), attendant);
    }

    @Override
    public void delete(AttendantId attendantId) {
        if (attendantId == null) {
            throw new IllegalArgumentException("The attendant ID can not be null");
        }
        attendants.remove(attendantId);
    }

    @Override
    public Attendant get(AttendantId attendantId) {
        if (attendantId == null) {
            throw new IllegalArgumentException("The attendant ID can not be null");
        }
        return attendants.get(attendantId); // Retorna o atendente ou null se não existir
    }

    @Override
    public void update(Attendant attendant) {
        if (attendant == null) {
            throw new IllegalArgumentException("The attendant can not be null");
        }
        attendants.put(attendant.getAttendantId(), attendant);
    }
}
