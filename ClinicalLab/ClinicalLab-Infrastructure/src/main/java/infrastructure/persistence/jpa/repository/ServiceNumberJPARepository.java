package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ServiceNumberJPA;

public interface ServiceNumberJPARepository extends JpaRepository<ServiceNumberJPA, Integer>{

}
