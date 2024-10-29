package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ServiceNumberJPA;
import infrastructure.persistence.jpa.TestResultJPA;

public interface ClientServiceRepository extends JpaRepository<TestResultJPA, Long>{

}
