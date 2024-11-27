package infrastructure.persistence.jpa;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.entities.clientservice.ClientServices;
import infrastructure.persistence.jpa.repository.ClientServiceJPARepository;
import domain.entities.clientservice.ClientServiceId;
import domain.entities.clientservice.ClientServiceRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

@Entity
@Table(name = "Clients_Service")
public class ClientServiceJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientServiceId;

    @OneToOne
    @JoinColumn(name = "Service_Number_Id")
    private ServiceNumberJPA serviceNumber;

    @OneToOne
    @JoinColumn(name = "Exam_Request_Id")
    private ExamRequestJPA examRequest;

    private String status;

    // Getters e Setters
    public Integer getClientServiceId() {
        return clientServiceId;
    }

    public void setClientServiceId(int clientServiceId) {
        this.clientServiceId = clientServiceId;
    }

    public ServiceNumberJPA getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(ServiceNumberJPA serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public ExamRequestJPA getExamRequest() {
        return examRequest;
    }

    public void setExamRequest(ExamRequestJPA examRequest) {
        this.examRequest = examRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

@Repository
class ClientServiceRepositoryImpl implements ClientServiceRepository {

    @Autowired
    private ClientServiceJPARepository clientServiceJPARepository;

    @Autowired
    private JPAMapper mapper;

    @Override
    public void save(ClientServices clientService) {
        ClientServiceJPA clientServiceJPA = mapper.map(clientService, ClientServiceJPA.class);
        clientServiceJPARepository.save(clientServiceJPA);
    }

    @Override
    public void delete(ClientServiceId id) {
        clientServiceJPARepository.deleteById(id.getId());
    }

    @Transactional
    @Override
    public ClientServices get(ClientServiceId id) {
        ClientServiceJPA clientServiceJPA = clientServiceJPARepository.findById(id.getId()).orElse(null);
        return mapper.map(clientServiceJPA, ClientServices.class);
    }

    @Override
    public void update(ClientServices clientService) {
        ClientServiceJPA clientServiceJPA = mapper.map(clientService, ClientServiceJPA.class);
        clientServiceJPARepository.save(clientServiceJPA);
    }
    
    @Override
    public List<ClientServices> getClientServicesAll() {
        List<ClientServiceJPA> clientServicesJPA = clientServiceJPARepository.findAll();
        return clientServicesJPA.stream()
                .map(clientServiceJPA -> mapper.map(clientServiceJPA, ClientServices.class))
                .collect(Collectors.toList());
    }
}