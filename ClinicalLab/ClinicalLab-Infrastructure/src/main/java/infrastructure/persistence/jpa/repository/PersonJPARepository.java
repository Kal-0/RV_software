package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.PersonJPA;

public interface PersonJPARepository extends JpaRepository<PersonJPA, Integer>{

}
