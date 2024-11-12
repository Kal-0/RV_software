package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.AttendantJPA;

public interface AttendantRepository extends JpaRepository<AttendantJPA, Integer>{

}
