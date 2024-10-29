package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ClientJPA;

public interface ClientRepository extends JpaRepository<ClientJPA, Long>{

}
