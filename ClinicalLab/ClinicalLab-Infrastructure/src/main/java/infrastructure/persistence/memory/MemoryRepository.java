package infrastructure.persistence.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import domain.common.entities.client.Client;
import domain.common.entities.client.ClientId;
import domain.common.entities.client.ClientRepository;
import domain.common.entities.exam.ExamRepository;
import domain.common.entities.person.Cpf;
import domain.common.entities.attendant.Attendant;
import domain.common.entities.attendant.AttendantId;
import domain.common.entities.attendant.AttendantRepository;
import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examrequest.ExamRequestId;
import domain.common.entities.examrequest.ExamRequestRepository;


/*implement the repositories here*/
public class MemoryRepository implements ClientRepository, AttendantRepository, ExamRequestRepository {
    private Map<ClientId, Client> clients = new HashMap<>();
    private Map<AttendantId, Attendant> attendants = new HashMap<>();
    private Map<ExamRequestId, ExamRequest> examRequests = new HashMap<>();
    

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
    
  
    public Client get(Cpf clientCPF) {
        if (clientCPF == null) {
            throw new IllegalArgumentException("The client CPF can not be null");
        }

        for (Client client : clients.values()) {
            if (client.getCpf().equals(clientCPF)) {
                 return client;
            }
        }

        return null;
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
    
	
    
    @Override
    public void save(ExamRequest examRequest) {
    	if (examRequest == null) {
            throw new IllegalArgumentException("The Exam Request can not be null");
        }
        examRequests.put(examRequest.getExamRequestId(), examRequest);
    }
    @Override
    public void delete(ExamRequestId examRequestId) {
    	if (examRequestId == null) {
            throw new IllegalArgumentException("The Exam Request ID can not be null");
        }
        examRequests.remove(examRequestId);
    }
    
    @Override
    public ExamRequest get(ExamRequestId examRequestId) {
        if (examRequestId == null) {
            throw new IllegalArgumentException("The Exam Request ID can not be null");
        }
        return examRequests.get(examRequestId);
    }
    
    @Override
    public void update(ExamRequest examRequest) {
    	if (examRequest == null) {
            throw new IllegalArgumentException("The Exam Request can not be null");
        }
        examRequests.put(examRequest.getExamRequestId(), examRequest);
    }
    
}
