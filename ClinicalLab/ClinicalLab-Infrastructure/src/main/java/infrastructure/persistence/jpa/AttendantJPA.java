package infrastructure.persistence.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import domain.entities.attendant.Attendant;
import domain.entities.attendant.AttendantId;
import domain.entities.attendant.AttendantRepository;

import infrastructure.persistence.jpa.repository.AttendantJPARepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Attendants")
@PrimaryKeyJoinColumn(name = "id") // Define que 'id' é a chave estrangeira que referencia 'People'
public class AttendantJPA extends PersonJPA {
	@Column(unique = true)
	private Integer attendantId;
	private String password;
   
	public int getAttendantId() {
		return attendantId;
	}
	public void setAttendantId(int attendantId) {
		this.attendantId = attendantId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}


@Repository
class AttendantRepositoryImpl implements AttendantRepository {

    @Autowired
    private AttendantJPARepository attendantJPARepository;

    @Autowired
    private JPAMapper mapper; // Usar o ExamMapper

    @Override
    public void save(Attendant attendant) {
        AttendantJPA attendantJPA = mapper.map(attendant, AttendantJPA.class);
        attendantJPARepository.save(attendantJPA);
    }
    
    @Override
    public void delete(AttendantId id) {
        attendantJPARepository.deleteByAttendantId(id.getId());
    }
    
//    @Override
//	public void delete(Cpf cpf) {
//		// TODO Auto-generated method stub
//    	attendantJPARepository.deleteByCpf(cpf.getCpf());
//	}

    @Override
    public Attendant get(AttendantId id) {
        AttendantJPA attendantJPA = attendantJPARepository.findByAttendantId(id.getId()).orElse(null);
        return mapper.map(attendantJPA, Attendant.class);
    }
    
//    @Override
//	public Attendant get(Cpf cpf) {
//		// TODO Auto-generated method stub
//    	AttendantJPA attendantJPA = attendantJPARepository.findByCpf(cpf.getCpf()).orElse(null);
//        return mapper.map(attendantJPA, Attendant.class);
//	}

//	@Override
//	public Attendant get(String name) {
//		// TODO Auto-generated method stub
//		AttendantJPA attendantJPA = attendantJPARepository.findByName(name).orElse(null);
//        return mapper.map(attendantJPA, Attendant.class);
//	}

    @Override
    public void update(Attendant attendant) {
        AttendantJPA attendantJPA = mapper.map(attendant, AttendantJPA.class);
        attendantJPARepository.save(attendantJPA);
    }
    
    @Override
    public List<Attendant> getAll() {
        List<AttendantJPA> attendantsJPA = attendantJPARepository.findAll();

        return attendantsJPA.stream()
                            .map(attendantJPA -> mapper.map(attendantJPA, Attendant.class))
                            .collect(Collectors.toList());
    }

	

	
}


