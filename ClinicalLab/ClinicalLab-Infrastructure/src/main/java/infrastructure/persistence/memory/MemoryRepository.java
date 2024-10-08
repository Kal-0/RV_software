package infrastructure.persistence.memory;

import java.util.HashMap;
import java.util.Map;
import domain.common.entities.client.Client;
import domain.common.entities.client.ClientId;
import domain.common.entities.client.ClientRepository;
import domain.common.entities.exam.Exam;
import domain.common.entities.exam.ExamId;
import domain.common.entities.exam.ExamRepository;
import domain.common.entities.person.Cpf;
import domain.common.entities.attendant.Attendant;
import domain.common.entities.attendant.AttendantId;
import domain.common.entities.attendant.AttendantRepository;
import domain.common.entities.examrequest.ExamRequest;
import domain.common.entities.examrequest.ExamRequestId;
import domain.common.entities.examrequest.ExamRequestRepository;
import domain.common.entities.examtest.ExamTest;
import domain.common.entities.examtest.ExamTestId;
import domain.common.entities.examtest.ExamTestRepository;


public class MemoryRepository implements ClientRepository, AttendantRepository, ExamRequestRepository, ExamRepository, ExamTestRepository {
    private Map<Integer, Client> clients = new HashMap<>();
    private Map<AttendantId, Attendant> attendants = new HashMap<>();
    private Map<ExamRequestId, ExamRequest> examRequests = new HashMap<>();
    
    
    
    @Override
    public void save(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client can not be null");
        }
//        System.out.println("*********"+get(client.getClientId())+"-VEF-");
        if(get(client.getClientId()) != null) {
//        	System.out.println("*********ERRO************");
        	throw new IllegalArgumentException("The client is alredy registered");
        }
        
    	clients.put(client.getClientId().getId(), client);
    }

    @Override
    public void delete(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("The client ID can not be null");
        }
        
        boolean found = false;
        
        for (Client client : clients.values()) {
            if (client.getId().getId() == (clientId.getId())) {
            	                 
                 clients.remove(clientId.getId());
                 
                 found = true;	
                 
                 break;
            }
        }
        
        if(!found) {
        	throw new IllegalArgumentException("Client not found with this Id");
        }
    }
    
    @Override
    public void delete(Cpf clientCpf) {
        if (clientCpf == null) {
            throw new IllegalArgumentException("The client ID can not be null");
        }
        
        boolean found = false;
        
        for (Client client : clients.values()) {
            if (client.getCpf().equals(clientCpf)) {
            	
                 ClientId clientId = client.getClientId();
                 
                 clients.remove(clientId.getId());
                 
                 found = true;
                 
                 break;
            }
        }
        
        if(!found) {
        	throw new IllegalArgumentException("Client not found with this CPF");
        }
    }

    @Override
    public Client get(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("The client ID can not be null");
        }
        
        
        return clients.get(clientId.getId());
        
        
    }
    
    @Override
    public Client get(Cpf clientCPF) {
        if (clientCPF == null) {
            throw new IllegalArgumentException("The client CPF can not be null");
        }

        for (Client client : clients.values()) {
        	
            if (client.getCpf().getCpf().equals(clientCPF.getCpf())) {
            	
                 return client;
            }
        }

        return null;
    }
    
    @Override
    public Client get(String clientName) {
        if (clientName == null) {
            throw new IllegalArgumentException("The client CPF can not be null");
        }

        for (Client client : clients.values()) {
            if (client.getName().equals(clientName)) {
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
        
        boolean found = false;
        
        for (Client cliente : clients.values()) {
            if (cliente.getId().equals(client.getId())) {
            	
        		clients.put(client.getClientId().getId(), client);
                 
                found = true;
                 
                break;
            }
        }
        if(!found) {
        	throw new IllegalArgumentException("Client cannot be updated");
        }
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
        return attendants.get(attendantId);
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
    

	@Override
	public void save(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ExamId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Exam get(ExamId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Exam exam) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void save(ExamTest examTest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ExamTestId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExamTest get(ExamTestId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ExamTest examTest) {
		// TODO Auto-generated method stub
		
	}

    
    
}
