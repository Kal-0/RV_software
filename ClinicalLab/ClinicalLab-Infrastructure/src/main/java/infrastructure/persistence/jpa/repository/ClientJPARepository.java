package infrastructure.persistence.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ClientJPA;

public interface ClientJPARepository extends JpaRepository<ClientJPA, Long>{
	
	 ClientJPA findByCpf(String cpf);
	 ClientJPA findByName(String name);

}
