package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ClientServiceJPA;
import infrastructure.persistence.jpa.TestResultJPA;

public interface TestResultJPARepository extends JpaRepository<TestResultJPA, Integer>{

}
