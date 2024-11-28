package infrastructure.persistence.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.entities.attendant.Attendant;
import domain.entities.attendant.AttendantId;
import domain.entities.attendant.AttendantRepository;
import domain.entities.client.Client;
import domain.entities.client.ClientId;
import domain.entities.client.ClientRepository;
import domain.entities.exam.Exam;
import domain.entities.exam.ExamId;
import domain.entities.exam.ExamRepository;
import domain.entities.examrequest.ExamRequest;
import domain.entities.examrequest.ExamRequestId;
import domain.entities.examrequest.ExamRequestRepository;
import domain.entities.examtest.ExamTest;
import domain.entities.examtest.ExamTestId;
import domain.entities.examtest.ExamTestRepository;
import domain.entities.person.Cpf;


public class MemoryRepository implements ClientRepository, AttendantRepository, ExamRequestRepository, ExamRepository, ExamTestRepository {
    private Map<ClientId, Client> clients = new HashMap<>();
    private Map<AttendantId, Attendant> attendants = new HashMap<>();
    private Map<ExamRequestId, ExamRequest> examRequests = new HashMap<>();
    
    
    
    @Override
    public Client save(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The client can not be null");
        }
//        System.out.println("*********"+get(client.getClientId())+"-VEF-");
        if(get(client.getClientId()) != null) {
//        	System.out.println("*********ERRO************");
        	throw new IllegalArgumentException("The client is alredy registered");
        }
        
    	clients.put(client.getClientId(), client);
    	return client;
    }

    @Override
    public void delete(ClientId clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("The client ID can not be null");
        }
        
        boolean found = false;
        
        for (Client client : clients.values()) {
            if (client.getId().equals(clientId)) {
            	                 
                 clients.remove(clientId);
                 
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
                 
                 clients.remove(clientId);
                 
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
        
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return clients.get(clientId);
        
        
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
	public List<Client> getClientAll() {
		// TODO Auto-generated method stub
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
            	
        		clients.put(client.getClientId(), client);
                 
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
    public ExamRequest save(ExamRequest examRequest) {
    	if (examRequest == null) {
            throw new IllegalArgumentException("The Exam Request can not be null");
        }
        examRequests.put(examRequest.getExamRequestId(), examRequest);
        return examRequest;
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
	public List<ExamRequest> getExamRequestAll() {
		// TODO Auto-generated method stub
		return null;
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
	public ExamTest save(ExamTest examTest) {
		// TODO Auto-generated method stub
		
		return examTest;
		
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

	@Override
	public List<ExamTest> getExamTestAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getExamAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attendant> getAttendantAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

    
    
}






