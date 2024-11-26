package infrastructure.persistence.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import domain.entities.servicenumber.ServiceNumber;
import domain.entities.servicenumber.ServiceNumberId;
import domain.entities.servicenumber.ServiceNumberRepository;
import infrastructure.persistence.jpa.repository.ServiceNumberJPARepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServiceNumber")
public class ServiceNumberJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String number;
	public boolean isPriority;
	public String status;
}


@Repository
class ServiceNumberRepositoryImpl implements ServiceNumberRepository {

    @Autowired
    private ServiceNumberJPARepository ServiceNumberJPARepository;

    @Autowired
    private JPAMapper mapper;

    @Override
    public void save(ServiceNumber serviceNumber) {
        ServiceNumberJPA serviceNumberJPA = mapper.map(serviceNumber, ServiceNumberJPA.class);
        ServiceNumberJPARepository.save(serviceNumberJPA);
    }
    
    @Override
    public void delete(ServiceNumberId id) {
        ServiceNumberJPARepository.deleteById(id.getId());
    }

    @Override
    public ServiceNumber get(ServiceNumberId id) {
        ServiceNumberJPA serviceNumberJPA = ServiceNumberJPARepository.findById(id.getId()).orElse(null);
        return mapper.map(serviceNumberJPA, ServiceNumber.class);
    }

    @Override
    public void update(ServiceNumber serviceNumber) {
        ServiceNumberJPA serviceNumberJPA = mapper.map(serviceNumber, ServiceNumberJPA.class);
        ServiceNumberJPARepository.save(serviceNumberJPA);
    }
    
    @Override
    public List<ServiceNumber> getAll() {
        List<ServiceNumberJPA> serviceNumbersJPA = ServiceNumberJPARepository.findAll();
        
        if (serviceNumbersJPA == null || serviceNumbersJPA.isEmpty()) {
            throw new NoSuchElementException("No ServiceNumbers found.");
        }
        
        return serviceNumbersJPA.stream()
                .map(serviceNumberJPA -> mapper.map(serviceNumberJPA, ServiceNumber.class))
                .collect(Collectors.toList());
    }
}
