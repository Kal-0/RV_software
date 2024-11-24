package infrastructure.persistence.jpa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.AttendantJPA;
import infrastructure.persistence.jpa.ClientJPA;

public interface AttendantJPARepository extends JpaRepository<AttendantJPA, Integer>{
	
	Optional<AttendantJPA> findByAttendantId(Integer attendantId);
	Optional<AttendantJPA> findByCpf(String cpf);
	Optional<AttendantJPA> findByName(String name);
	Optional<AttendantJPA> deleteByAttendantId(Integer attendantId);
	Optional<AttendantJPA> deleteByCpf(String cpf);
}
