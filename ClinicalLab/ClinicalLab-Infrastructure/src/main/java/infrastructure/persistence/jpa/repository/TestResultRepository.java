package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ClientServiceJPA;

public interface TestResultRepository extends JpaRepository<ClientServiceJPA, Long>{

}
