package infrastructure.persistence.jpa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import infrastructure.persistence.jpa.ClientJPA;

public interface ClientJPARepository extends JpaRepository<ClientJPA, Integer>{
	
	Optional<ClientJPA> findByClientId(Integer clientId);
	Optional<ClientJPA> findByCpf(String cpf);
	Optional<ClientJPA> findByName(String name);
	void deleteByClientId(Integer clientId);
	void deleteByCpf(String cpf);

}
